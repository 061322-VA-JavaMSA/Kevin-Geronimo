async function getReimbursements(){
    route()

    let apiUrl = 'http://localhost:8080/ers/reimbursements';
    let response = await fetch(`${apiUrl}/reimbursements`, {
        credentials: 'include'
    });

    if(response.status == 200){
        let data = await response.json();

        populateTable(data);
    } else{
        console.log('Unable to retrieve reimbursements.')
    }

}

function populateTable(data){
    let tableBody = document.getElementById('reimbursements');

    data.forEach(reimbursement => {
        let td_class = "text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap";
        let tr = document.createElement('tr');
        tr.className = "bg-white border-b transition duration-300 ease-in-out hover:bg-gray-100";

        let tdAmount = document.createElement('td');
        tdAmount.className = td_class;
        let tdDescription = document.createElement('td');
        tdDescription.className = td_class += " max-w-xs whitespace-normal truncate";
        tdDescription.classList.remove("whitespace-nowrap");
        let tdType = document.createElement('td');
        tdType.className = td_class;
        let tdSubmitted = document.createElement('td');
        tdSubmitted.className = td_class;
        let tdReceipt = document.createElement('td');
        tdReceipt.className = td_class;
        let tdStatus = document.createElement('td');
        tdStatus.className = td_class;

        let img = document.createElement('img');
        img.src = "data:image/jpeg;base64," + reimbursement.receipt;
        img.className = "h-14 w-14";

        tdAmount.innerHTML = reimbursement.amount;
        tdDescription.innerHTML = reimbursement.description;
        tdType.innerHTML = reimbursement.ersReimbType.type;
        tdSubmitted.innerHTML = reimbursement.dateSubmitted;
        tdReceipt.appendChild(img);
        tdStatus.innerHTML = reimbursement.ersReimbStatus.status;

        tr.append(tdAmount);
        tr.append(tdDescription);
        tr.append(tdType);
        tr.append(tdSubmitted);
        tr.append(tdReceipt);
        tr.append(tdStatus);

        tableBody.append(tr);
    });
}