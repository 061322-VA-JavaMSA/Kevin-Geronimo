function showFileName( event ) {
    let infoArea = document.getElementById('file-name');
    let input = event.srcElement;
    let fileName = input.files[0].name;
    console.log(fileName);
    infoArea.textContent = 'File name: ' + fileName;
  }

  async function reimbFormHandler(event) {
    event.preventDefault();
    let apiUrl = 'http://localhost:8080/ers';

    // let formData = new FormData(event.target);
    // for(const pair of formData.entries()) {
    //     console.log(`${pair[0]}, ${pair[1]}`);
    //   }

    let response = await fetch(`${apiUrl}/reimbursements`, {
        method: 'POST',
        credentials: 'include',
        body: new FormData(event.target)
    });

    if (response.status == 200) {
        console.log(response)
    } else {
        console.log(response)
    }

    event.target.reset();
    document.getElementById('file-name').textContent = "PNG, JPG, GIF up to 10MB"
};