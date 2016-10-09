var codeProgram = sessionStorage.getItem("codeProgram");

$(document).ready(function () {
    console.log("codeProgram: " + codeProgram);
    getSpecificProgram(codeProgram);

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
            //$('#select-college>option:eq( ' + data.college + ')').prop('selected', true);
            $('#units').val(data.units);
            $('#description').val(data.description);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

