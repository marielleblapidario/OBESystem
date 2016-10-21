var curriculum = $('#curriculum-title');
var course = $('#course-title');
var term = $('#term');
var startYear = $('#startYear');
var endYear = $('#endYear');
var divCO = $("#div-addCO");
var divAssessment = $("#div-addAssessment");
var table = $("#data");
var tableA = $("#data-assessment");
var syllabusID = sessionStorage.getItem("syllabusID");
var rowCount = 0;
var rowCountA = 0;

$(document).ready(function () {
    console.log("syllabusID: ", syllabusID);
    getSyllabus(syllabusID);
    getAllCO(syllabusID);
    getAllAssessment(syllabusID);
});

function getSyllabus(syllabusID) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificSyllabus?syllabusID=" + syllabusID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            curriculum.val(data.curriculumTitle);
            course.val(data.courseTitle);
            startYear.val(data.startYear);
            endYear.val(data.endYear);
            term.val(data.term);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllCO(syllabusID) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllCOofSyllabus?syllabusID=" + syllabusID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(getCO);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllAssessment(syllabusID) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllAssessmentOfSyllabus?syllabusID=" + syllabusID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(getAssessment);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getCO(data) {
    var codeCO = data.codeCO;
    var coDescription = data.coDescription;
    var codePI = data.codePI;
    var coRemarks = data.coRemarks;

    console.log("rowCount: ", rowCount);
    var s = '<tr id =tr' + rowCount + '>'
            + '<td>' + codeCO + '</td>'
            + '<td>' + coDescription + '</td>'
            + '<td>' + codePI + '</td>'
            + '<td>' + coRemarks + '</td>'
            + '<tr>';
    table.append(s);
    rowCount++;
}

function getAssessment(data) {
    var codeAT = data.codeAT;
    var assessmentTitle = data.assessmentTitle;
    var codeCO = data.codeCO;
    var assessmentDescription = data.assessmentDescription;
    var assessmentWeight = data.assessmentWeight;

    console.log("rowCount: ", rowCountA);
    var s = '<tr id =tr' + rowCountA + '>'
            + '<td>' + codeAT + '</td>'
            + '<td>' + assessmentTitle + '</td>'
            + '<td>' + codeCO + '</td>'
            + '<td>' + assessmentDescription + '</td>'
            + '<td>' + assessmentWeight + '</td>'
            + '<tr>';
    tableA.append(s);
    rowCountA++;
}