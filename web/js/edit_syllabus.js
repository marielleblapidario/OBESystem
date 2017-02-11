var curriculum = $('#curriculum-title');
var course = $('#course-title');
var term = $('#term');
var divCO = $("#div-addCO");
var divAssessment = $("#div-addAssessment");
var table = $("#data");
var tableA = $("#data-assessment");
var syllabusID = sessionStorage.getItem("syllabusID");
var yearPicker = $('#startYear');
var rowCount = 0;
var rowCountA = 0;
var arrPI = [];

$(document).ready(function(){
    console.log("syllabusID: ", syllabusID);
    
});

function getAllType() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllType",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrType.push(data[x]);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllTerm() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllTrimester",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(showTerm);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getSyllabus(syllabusID) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificSyllabus?syllabusID=" + syllabusID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            
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
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllPI(curriculumID, courseID) {
    console.log("curriculumID: " + curriculumID);
    console.log("courseID: " + courseID);
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetPIforCO?curriculumID=" + curriculumID
                + "&courseID=" + courseID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrPI.push(data[x]);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function changeYear() {
    yearPicker.on("input change", function (e) {
        console.log("changed: ", e.target.value);
        var sYear = +e.target.value;
        var eYear = sYear + 1;
        $("#endYear").val(eYear);
    });
}