var codeProgram = sessionStorage.getItem("codeProgram");

$(document).ready(function () {
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
            $('#units').val(data.units);
            $('#description').val(data.description);
        },
        error: function (response) {
            console.log(response);
        }
    });
}