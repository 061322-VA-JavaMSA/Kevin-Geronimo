package com.revature.servlets;

import com.revature.exceptions.ReimbNotCreatedException;
import com.revature.exceptions.UserNotCreatedException;
import com.revature.models.ERSReimbStatus;
import com.revature.models.ERSReimbType;
import com.revature.models.ERSReimbursement;
import com.revature.models.ERSUser;

import com.revature.services.ReimbService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDate;

@MultipartConfig
public class ReimbServlet extends HttpServlet {
    private final ReimbService reimbService = new ReimbService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ok = request.getParameter("amount");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        String description = request.getParameter("description");
        int reimbTypeId = Integer.parseInt(request.getParameter("reimbursement_type_id"));
        HttpSession session = request.getSession();

        Part filePart = request.getPart("receipt"); // Retrieves <input type="file" name="receipt">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();

        ERSReimbursement ersReimbursement = new ERSReimbursement();
        ersReimbursement.setAmount(amount);
        ersReimbursement.setAuthor(new ERSUser());
        ersReimbursement.getAuthor().setId((Integer) session.getAttribute("userId"));
        ersReimbursement.setDescription(description);
        ersReimbursement.setErsReimbType(new ERSReimbType());
        ersReimbursement.getErsReimbType().setId(reimbTypeId);
        ersReimbursement.setDateSubmitted(LocalDate.now());
        ersReimbursement.setErsReimbStatus(new ERSReimbStatus());
        ersReimbursement.getErsReimbStatus().setId(3);
        //https://www.baeldung.com/convert-input-stream-to-array-of-bytes
        byte[] targetArray = new byte[fileContent.available()];
        fileContent.read(targetArray);
        ersReimbursement.setReceipt(targetArray);

        try {
            reimbService.createReimb(ersReimbursement);
            try (PrintWriter pw = response.getWriter()) {
                pw.write("Good");
                response.setStatus(200);
            }
        } catch (ReimbNotCreatedException e) {
            throw new RuntimeException(e);
        }
    }
}
