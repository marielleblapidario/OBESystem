var codeCurriculum = sessionStorage.getItem("codeCurriculum");
var tableDIV = $("#table-div");
var sizePI = 45;
var table;

var courseCodes = [];
var mapping = [];

appendTable();
getCourses(codeCurriculum);
$(document).ready(function() {
    $('#save-btn').click(function() {
        console.log("clicked");
        var elements = document.getElementsByClassName('checkbox');
        for (var i = 0; elements[i]; i++) {
            if (elements[i].checked) {
                var value = elements[i].value.split("_");
                var courseCode = value[0];
                var PI = value[1];
                var mapCombo = {courseCode: courseCode, PI: PI};
                mapping.push(mapCombo);
                console.log(courseCode + " is checked on " + PI);
            }
        }

        for (var x = 0; mapping[x]; x++) {
            var courseCode = mapping[x].courseCode;
            var PI = mapping[x].PI;
            $.ajax({
                type: "GET",
                url: "/OBESystem/SampleMapping?courseCode=" + courseCode + "&PI=" + PI,
                dataType: 'json',
                success: function(data) {
                    console.log(data);
                },
                error: function(response) {
                    console.log(response);
                }
            });
        }
    });
});
function appendTable() {
    var s = "<table class='table table-bordered' id='table' style='overflow-y:auto; width=100%; text-align:center;'>";
    tableDIV.append(s);
    table = $("#table");
    var ss = "<tr id='table-header'>";
    table.append(ss);
    var header = $("#table-header");
    header.append("<td> Code </td>");
    header.append("<td> Units  </td>");
    for (var i = 1; i <= sizePI; i++) {
        var a = "<td> PO-BSINSYS-01." + i + " </td>";
        header.append(a);
    }
    header.append("</tr>");
}

function getCourses(codeCurriculum) {
    console.log("codeCurriulum for courses: " + codeCurriculum);
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificMapCurriculumToCourse?SelectedCurriculum=" + codeCurriculum,
        dataType: 'json',
        success: function(data) {
            console.log(data);
            data.forEach(appendTableRow);
        },
        error: function(response) {
            console.log(response);
        }
    });
}
function appendTableRow(data) {
    var courseID = data.courseID;
    var codeCourse = data.codeCourse;
    var title = data.title;
    var units = data.units;
    var PI = "PO-BSINSYS-01";
    courseCodes.push(codeCourse);
    var s = "<tr id=" + codeCourse + "> " +
            +"<td>" + codeCourse + "</td>"
            + "<td>" + codeCourse + "</td>"
            + "<td>" + units + "</td>";
    table.append(s);
    var row = $("#" + codeCourse);
    for (var i = 1; i <= sizePI; i++) {
        var appendPI = "<td>"
                + "<label class=''>"
                + "<div style='position:relative;'>"
                + "<input value='" + codeCourse + "_" + PI + i + "' type='checkbox' class='checkbox'></div>"
                + "</label>"
                + "</td>";
        row.append(appendPI);
    }
    row.append("</tr>");
}