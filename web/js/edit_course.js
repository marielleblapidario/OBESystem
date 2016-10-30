var courseID = sessionStorage.getItem("courseID");
var programDropdown = $("#select-program");
var arrProgram = [];

$(document).ready(function () {
    console.log("courseID: " + courseID);
    getSpecificCourse(courseID);
    getMapCourseToProgram(courseID);
});

function getSpecificCourse(courseID) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificCourse?SelectedCourse=" + courseID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            $('#title').val(data.title);
            $('#codeCourse').val(data.codeCourse);
            $('#courseID').val(data.courseID);
            setUnits(data.units);
            $('#description').val(data.description);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getMapCourseToProgram(courseID) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificMapCourseToProgram?SelectedCourse=" + courseID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrProgram.push(data[x]);
            }
            console.log("arrProgram size: ", arrProgram.length);
            getAllProgram();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllProgram() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/ViewProgramList",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var tr;
            for (var x = 0; x < data.length; x++) {
                var check = false;
                for (var y = 0; y < arrProgram.length; y++) {
                    if (arrProgram[y].codeProgram == data[x].codeProgram) {
                        console.log("entered if");
                        check = true;
                    }
                }
                if (check) {
                    var s = "<option value=" + data[x].codeProgram + " selected>" + data[x].title + "</option>";
                    tr += s;
                } else {
                    var s = "<option value=" + data[x].codeProgram + ">" + data[x].title + "</option>";
                    tr += s;
                }
            }
            programDropdown.append(tr);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function setUnits(units) {
    var tr;
    for (var x = 0; x < 4; x++) {
        if (units == x) {
            console.log("entered if");
            var s = "<option value=" + x + " selected>" + x + "</option>";
            tr += s;
        } else {
            var s = "<option value=" + x + ">" + x + "</option>";
            tr += s;
        }
    }
    $('#units').append(tr);
}