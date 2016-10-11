var codeCurriculum = sessionStorage.getItem("codeCurriculum");
var tableDIV = $("#table-div");
var sizePI = 0;
var table;
var courseCodes = [];
var mapping = [];
var arrPI = [];
var arrCodePI = [];
var arrCourseID = [];

$(document).ready(function () {
    getMapping(codeCurriculum);
    $('#save-btn').click(function () {
        console.log("clicked");
        var elements = document.getElementsByClassName('checkbox');
        for (var i = 0; elements[i]; i++) {
            if (elements[i].checked) {
                var value = elements[i].value.split("_");
                var courseID = value[0];
                var PI = value[1];
                var mapCombo = {courseID: courseID, codePI: PI, curriculumID: codeCurriculum};
                mapping.push(mapCombo);
                console.log(courseID + " is checked on " + PI + " curriculumID: " + codeCurriculum);
            }
        }
        var jsonData = JSON.stringify(mapping);
        $.ajax({
            type: "POST",
            url: "/OBESystem/EncodeMapCurriculumToPI",
            dataType: 'json',
            data: {'jsonData': jsonData},
            success: function (data) {
                console.log(data);
            },
            error: function (response) {
                console.log(response);
            }
        });
    });
});

function getMapping(codeCurriculum) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetMapCurriculumToPI?SelectedCurriculum=" + codeCurriculum,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrCourseID.push(data[x].courseID);
                arrCodePI.push(data[x].codePI);
            }
            getSpecificCurriculum(codeCurriculum);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getCourses(codeCurriculum) {
    console.log("codeCurriulum for courses: " + codeCurriculum);
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificMapCurriculumToCourse?SelectedCurriculum=" + codeCurriculum,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(appendTableRow);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getSpecificCurriculum(codeCurriculum) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificCurriculum?SelectedCurriculum=" + codeCurriculum,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            $('title').text(data.title);
            var codeProgram = data.program;

            $.ajax({
                type: "GET",
                url: "/OBESystem/GetAllPIforCurriculum?program=" + codeProgram,
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    var s = "<table class='table table-bordered' id='table' style='overflow-y:auto; width=100%; text-align:center;'>";
                    tableDIV.append(s);
                    table = $("#table");
                    var ss = "<tr id='table-header'>";
                    table.append(ss);
                    var header = $("#table-header");
                    header.append("<td> Code </td>");
                    header.append("<td> Units  </td>");

                    sizePI = data.length;

                    for (var i = 0; i < data.length; i++) {
                        var a = "<td>" + data[i].codePI + " </td>";
                        header.append(a);
                        arrPI.push(data[i].codePI);
                    }
                    header.append("</tr>");
                    getCourses(codeCurriculum);

                },
                error: function (response) {
                    console.log(response);
                }
            });

        },
        error: function (response) {
            console.log(response);
        }
    });
}
function appendTableRow(data) {
    var courseID = data.courseID;
    var codeCourse = data.codeCourse;
    var units = data.units;
    courseCodes.push(codeCourse);
    var s = "<tr id=" + codeCourse + "> " +
            +"<td>" + codeCourse + "</td>"
            + "<td>" + codeCourse + "</td>"
            + "<td>" + units + "</td>";
    table.append(s);
    var row = $("#" + codeCourse);
    for (var i = 0; i < arrPI.length; i++) {
        var codePI = courseID + "_" + arrPI[i];
        var checked = "";
        for (var x = 0; x < arrCodePI.length; x++) {
            if (courseID == arrCourseID[x]) {
                if (arrPI[i] == arrCodePI[x]) {
                    checked = "checked";
                }
            }
        }
        var appendPI = "<td>"
                + "<label class=''>"
                + "<div style='position:relative;'>"
                + "<input value='" + codePI + "' type='checkbox' class='checkbox'" + checked + "></div>"
                + "</label>"
                + "</td>";
        row.append(appendPI);
    }
    row.append("</tr>");
}