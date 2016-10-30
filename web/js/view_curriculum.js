var codeCurriculum = sessionStorage.getItem("codeCurriculum");
var table = $("#data");
var spanUnits = $("#total-units");
var rowCount = 0;
var totalUnits = 0;

$(document).ready(function () {
    getSpecificCurriculum(codeCurriculum);
    getSpecificMapCurriculumToCourse(codeCurriculum);
});

function getSpecificCurriculum(codeCurriculum) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificCurriculum?SelectedCurriculum=" + codeCurriculum,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            $("#title").val(data.title);
            $("#programName").val(data.programName);
            $("#startYear").val(data.startYear);
            $("#endYear").val(data.endYear);
            $("#description").val(data.description);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getSpecificMapCurriculumToCourse(codeCurriculum) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificMapCurriculumToCourse?SelectedCurriculum=" + codeCurriculum,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(addRow);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addRow(data) {
    var courseID = data.courseID;
    var codeCourse = data.codeCourse;
    var courseTitle = data.courseTitle;
    var units = data.units;
    var term = data.term;
    var yearLevel = data.yearLevel;
    var preRequisite = data.preRequisite;
    var pTitle = data.prerequisiteTitle;
    
    if(!pTitle){
        pTitle = "-";
    }
    
    totalUnits = totalUnits + parseInt(units);
    var tr = '';
    var appendTr = '<tr id =tr' + rowCount + '>'
            + '<td>' + codeCourse
            + '<input type="hidden" name="codeCourse" class="readonlyWhite" value="' + codeCourse + '" />'
            + '<input type="hidden" name="courseID" class="readonlyWhite" value="' + courseID + '" />'
            + '</td>'
            + '<td>' + courseTitle
            + '<input type="hidden" name="title" class="readonlyWhite"  value="' + courseTitle + '" />'
            + '</td>'
            + '<td>' + units
            + '<input type="hidden" name="title" class="readonlyWhite"  value="' + units + '" />'
            + '</td>'
            + '<td>' + yearLevel
            + '<input type="hidden" name="title" class="readonlyWhite"  value="' + yearLevel + '" />'
            + '</td>'
            + '<td>' + term
            + '<input type="hidden" name="title" class="readonlyWhite"  value="' + term + '" />'
            + '</td>'
            + '<td>' + pTitle
            + '<input type="hidden" name="title" class="readonlyWhite"  value="' + preRequisite + '" />'
            + '</td>';
    tr += appendTr;
    table.append(tr);
    rowCount++;
    spanUnits.text(totalUnits);
}