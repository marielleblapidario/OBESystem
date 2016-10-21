var codeProgram = sessionStorage.getItem("codeProgram");
var collegeDropDown = $("#select-college");
var college = -1;

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
            college = data.college;
            console.log("collegeID: ",college);
            $('#description').val(data.description);
            getAllCollege();

        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllCollege() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllColleges",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var tr;
            for (var x = 0; x < data.length; x++) {
                console.log(college + " vs " + data[x].collegeID);
                if (college == data[x].collegeID) {
                    console.log("entered if");
                    var s = "<option value=" + data[x].collegeID + " selected>" + data[x].college + "</option>";
                    tr += s;
                } else {
                    var s = "<option value=" + data[x].collegeID + ">" + data[x].college + "</option>";
                    tr += s;
                }
            }
            collegeDropDown.append(tr);
        },
        error: function (response) {
            console.log(response);
        }
    });
}