var codeProgram = sessionStorage.getItem("codeProgram");
var posID = sessionStorage.getItem("posID");
var userID = sessionStorage.getItem("userID");
console.log("posID: ", posID);
console.log("userID: ", userID);

$(document).ready(function () {
    if (posID == 6 || posID == 1) {
    } else {
        $('#edit').hide();
    }
    console.log("codeProgram: " + codeProgram);
    getSpecificProgram(codeProgram);

    $('#edit').click(function () {
        sessionStorage.setItem("codeProgram", codeProgram);
        var ss = sessionStorage.getItem("codeProgram");
        console.log("codeProgram: " + ss);
    });
});

function getSpecificProgram(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificProgram?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            $('#title').val(data.title);
            $('#codeProgram').val(data.codeProgram);
            $('#college').val(data.collegeName);
            $('#description').val(data.description);
        },
        error: function (response) {
            console.log(response);
        }
    });
}