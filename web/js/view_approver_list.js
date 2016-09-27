var approverDropDown = $('#select-approver');

$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllApprover",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(addApprover);
        },
        error: function (response) {
            console.log(response);
        }
    });
});

function addApprover(data){
    console.log("entered user: " + data.fullName);
    var s = "<option value = " + data.userID + ">" + data.fullName + "</option>";   
    approverDropDown.append(s);
}