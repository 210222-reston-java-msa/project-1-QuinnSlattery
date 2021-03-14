    function submitUpdate() {
    console.log("Employee info update triggered");

    let newFirstName = document.getElementById('empFirstName').value;
    let newLastName = document.getElementById('empLastName').value;
    let newEmail = document.getElementById('empEmail').value;
    let newPassword = document.getElementById('accPassword').value;
    var myUser = JSON.parse(sessionStorage.getItem('currentUser'));
    let theirUsername = myUser.username;

    console.log(`FirstName: ${newFirstName}`);
    console.log(`LastName: ${newLastName}`);
    console.log(`Email: ${newEmail}`);
    console.log(`Password: ${newPassword}`);
    console.log(`Username: ${theirUsername}`);

    let infoTemplate = {
        username: theirUsername,
        firstName: newFirstName,
        lastName: newLastName,
        email: newEmail,
        password: newPassword
    }

    let xhr = new XMLHttpRequest();
        
        
        xhr.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                console.log("success");
        
                sessionStorage.setItem('userInformation', this.responseText);
        
                window.location = "http://localhost:8080/project-1/EmployeeHome.html";
        
                console.log('Below is the new userInformation session storage item:-----------------------------');
                console.log(sessionStorage.getItem('userInformation'));
            }
        
            if (this.readyState === 4 && this.status === 204) { 
        
                console.log("failed");
        
                let childDiv = document.getElementById('warningText');
            }
        }
        
        console.log("here's the infoTemplate:");
        console.log(infoTemplate);
        console.log("there it was");

        xhr.open("POST", "http://localhost:8080/project-1/updateInfo");
        
        xhr.send(JSON.stringify(infoTemplate));

}


function submitRequestChange() {
    
    let reimbursementId = document.getElementById('reimbId').value;
    let reimbStatusId = document.getElementById('statusId').value;

    var theResolver = JSON.parse(sessionStorage.getItem('currentUser'));

    let changeTemplate = {
        reimbId: reimbursementId,
        statusId: reimbStatusId,
        resolver: theResolver
    }

    let xhr = new XMLHttpRequest();
        
        
    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");
    
            window.location = "http://localhost:8080/project-1/ManagerHome.html";

        }
    
        if (this.readyState === 4 && this.status === 204) { 
    
            console.log("failed");
    
            let childDiv = document.getElementById('warningText');
        }
    }
    
    console.log("here's the changeTemplate:");
    console.log(changeTemplate);
    console.log("there it was");

    xhr.open("POST", "http://localhost:8080/project-1/approveDeny");
    
    xhr.send(JSON.stringify(changeTemplate));
        
    }
   



function submitRequest() {

        console.log("Reimbursement request triggered");
        
        let reimbAmount = document.getElementById('reimbAmount').value;
        
        let reimbDescription = document.getElementById('reimbDescription').value;
        
        let reimbType = document.getElementById('reimbType').value;
        
        var myUser = JSON.parse(sessionStorage.getItem('currentUser'));

        let reimbAuthor = myUser.username;
        
        console.log(`Amount: ${reimbAmount}`);
        console.log(`Description: ${reimbDescription}`);
        console.log(`Author: ${reimbAuthor}`);
        console.log(`Type: ${reimbType}`);
        
        
        let reimbTemplate = {
            amount: reimbAmount,
            description: reimbDescription,
            author: reimbAuthor,
            typeId: reimbType
            }
            
            let xhr = new XMLHttpRequest();
        
        
        xhr.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                console.log("success");
        
                sessionStorage.setItem('currentReimbursement', this.responseText);
        
                window.location = "http://localhost:8080/project-1/EmployeeHome.html";
        
                console.log(sessionStorage.getItem('currentReimbursement'));
            }
        
            if (this.readyState === 4 && this.status === 204) { 
        
                console.log("failed");
        
                let childDiv = document.getElementById('warningText');
            }
        }
        
        console.log("here's the reimbTemplate:");
        console.log(reimbTemplate);
        console.log("there it was");

        xhr.open("POST", "http://localhost:8080/project-1/reimbursement");
        
        xhr.send(JSON.stringify(reimbTemplate));
            
        }









function sendLoginEmployee() {
    
	console.log("send login triggered");

    let uName = document.getElementById('uName').value;

    let pWord = document.getElementById('pWord').value;

    console.log(`Username: ${uName}`);
    console.log("testing");
    console.log(`Password: ${pWord}`);

    let loginTemplate = {
        username: uName,
        password: pWord
    }


    let xhr = new XMLHttpRequest();


    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");

            sessionStorage.setItem('currentUser', this.responseText);

            window.location = "http://localhost:8080/project-1/EmployeeHome.html";

            console.log('Below should be the current user:')

            console.log('here is the current user: ' + sessionStorage.getItem('currentUser'));


        }

        if (this.readyState === 4 && this.status === 204) { 

            console.log("failed to find user");

            let childDiv = document.getElementById('warningText');
            childDiv.textContent = "Failed to login! Check username and password. If you are a manager, please sign in on the appropriate page."
        }
    }
    
    xhr.open("POST", "http://localhost:8080/project-1/loginEmployee");

    xhr.send(JSON.stringify(loginTemplate));

}




function sendLoginManager() {
    
	console.log("send login triggered");

    let uName = document.getElementById('uName').value;

    let pWord = document.getElementById('pWord').value;

    console.log(`Username: ${uName}`);
    console.log("testing");
    console.log(`Password: ${pWord}`);

    let loginTemplate = {
        username: uName,
        password: pWord
    }


    let xhr = new XMLHttpRequest();


    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");

            sessionStorage.setItem('currentUser', JSON.stringify(loginTemplate))

            window.location = "http://localhost:8080/project-1/ManagerHome.html";

            console.log(sessionStorage.getItem('currentUser'));
        }

        if (this.readyState === 4 && this.status === 204) { 

            console.log("failed to find user");

            let childDiv = document.getElementById('warningText');
            childDiv.textContent = "Failed to login! Check username and password. Make sure you are registered as a Manager."
        }
    }
    
    xhr.open("POST", "http://localhost:8080/project-1/loginManager");

    xhr.send(JSON.stringify(loginTemplate));

}

function employeePage() {
window.location = "http://localhost:8080/project-1/EmployeeLogin.html";
}

function managerPage() {
window.location = "http://localhost:8080/project-1/ManagerLogin.html";
}




function pendingReimbursements() {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");
    
            sessionStorage.setItem('pendingReimbArray', this.responseText);

            console.log('Here is the array below:');
            console.log(sessionStorage.getItem('pendingReimbArray'));
            console.log('There was the array');
    
            window.location = "http://localhost:8080/project-1/PendingReimbursements.html";
        }
    
        if (this.readyState === 4 && this.status === 204) { 
    
            console.log("failed");
    
            let childDiv = document.getElementById('warningText');
        }
    }

    xhr.open("POST", "http://localhost:8080/project-1/pendingReimbursements");
    
    xhr.send();
}

function resolvedReimbursements() {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");
    
            sessionStorage.setItem('resolvedReimbArray', this.responseText);

            console.log('Here is the array below:');
            console.log(sessionStorage.getItem('resolvedReimbArray'));
            console.log('There was the array');
    
            window.location = "http://localhost:8080/project-1/ResolvedReimbursements.html";
        }
    
        if (this.readyState === 4 && this.status === 204) { 
    
            console.log("failed");
    
            let childDiv = document.getElementById('warningText');
        }
    }

    xhr.open("POST", "http://localhost:8080/project-1/resolvedReimbursements");
    
    xhr.send();
}




function allEmployees() {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");
    
            sessionStorage.setItem('employeeArray', this.responseText);

            console.log('Here is the employee array below:');
            console.log(sessionStorage.getItem('employeeArray'));
            console.log('There was the array');
    
            window.location = "http://localhost:8080/project-1/AllEmployees.html";
        }
    
        if (this.readyState === 4 && this.status === 204) { 
    
            console.log("failed");
    
            let childDiv = document.getElementById('warningText');
        }
    }

    xhr.open("POST", "http://localhost:8080/project-1/allEmployees");
    
    xhr.send();
}



function singleEmployeeReimbursements() {
    
    let employeeUsername = document.getElementById('employeeUsername').value;

        let empTemplate = {
            username: employeeUsername
        }
            let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                console.log("success");
        
                sessionStorage.setItem('reimbArray', this.responseText);

                console.log('Here is the array below:');
                console.log(sessionStorage.getItem('reimbArray'));
                console.log('There was the array');
        
                window.location = "http://localhost:8080/project-1/EmployeeReimbursements.html";
            }
        
            if (this.readyState === 4 && this.status === 204) { 
        
                console.log("failed");
        
                let childDiv = document.getElementById('warningText');
            }
        }
        

        xhr.open("POST", "http://localhost:8080/project-1/employeeReimbursements");
        
        xhr.send(JSON.stringify(empTemplate));


}






function employeeReimbursements() {
    
    var myUser = JSON.parse(sessionStorage.getItem('currentUser'));

        let uName = myUser.username;
        let pWord = myUser.password;

        let loginTemplate = {
            username: uName,
            password: pWord
        }


            let xhr = new XMLHttpRequest();
        
        
        xhr.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                console.log("success");
        
                sessionStorage.setItem('reimbArray', this.responseText);

                console.log('Here is the array below:');
                console.log(sessionStorage.getItem('reimbArray'));
                console.log('There was the array');
        
                window.location = "http://localhost:8080/project-1/EmployeeReimbursements.html";
            }
        
            if (this.readyState === 4 && this.status === 204) { 
        
                console.log("failed");
        
                let childDiv = document.getElementById('warningText');
            }
        }
        

        xhr.open("POST", "http://localhost:8080/project-1/employeeReimbursements");
        
        xhr.send(JSON.stringify(loginTemplate));


}

/*
Employee info ---------------------------------------------------------------------------------------------------------------
*/



function employeeInfo() {

    var myUser = JSON.parse(sessionStorage.getItem('currentUser'));

        let uName = myUser.username;
        let pWord = myUser.password;

        let loginTemplate = {
            username: uName,
            password: pWord
        }


            let xhr = new XMLHttpRequest();
        
        
        xhr.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                console.log("success");
        
                sessionStorage.setItem('userInformation', this.responseText);

                console.log('Here is the user information below:');
                console.log(sessionStorage.getItem('userInformation'));
                console.log('There was the user info');
        
                window.location = "http://localhost:8080/project-1/EmployeeInfo.html";
            }
        
            if (this.readyState === 4 && this.status === 204) { 
        
                console.log("failed");
        
                let childDiv = document.getElementById('warningText');
            }
        }
        

        xhr.open("POST", "http://localhost:8080/project-1/employeeInfo");
        
        xhr.send(JSON.stringify(loginTemplate));
}