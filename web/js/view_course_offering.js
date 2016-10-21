var offeringID = sessionStorage.getItem("offeringID");
var syllabusID = sessionStorage.getItem("syllabusID");
var curriculum = $('#curriculum-title');
var course = $('#course-title');
var hiddenCurriculum = $('#hidden-curriculum-title');
var hiddenCourse = $('#hidden-course-title');
var hiddenSyllabus = $('#hidden-syllabusID');
var term = $('#term');
var startYear = $('#startYear');
var endYear = $('#endYear');
var section = $('#section');
var days = $('#days');
var time = $('#time');
var room = $('#room');
var faculty = $('#faculty');
var arrEnrolledStudents = [];
// arr of objects (e.g. studentID: 11335998, firstName: Marielle, lastName: Lapidario, middleName: Bravo)
var arrColumnName = [];
// arr of strings (e.g. studentID, firstName, lastName, middleName) not sure if
// needed pa since sa arrEnrolledStudents nakalagay naman na yung column names of each attribute of the object)
var arrAssessment = [];
// arr of strings (e.g. AS - 01, AS - 02, AS - 03)

$(document).ready(function () {
    getSyllabus(offeringID);
    getEnrolledStudents(offeringID);
    getAssessments(syllabusID);
    getColumnNames();
});

function getSyllabus(offeringID) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificOffering?offeringID=" + offeringID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            curriculum.val(data.curriculumTitle);
            hiddenCurriculum.val(data.curriculumID);
            hiddenSyllabus.val(data.syllabusID);
            course.val(data.courseTitle);
            hiddenCourse.val(data.courseID);
            startYear.val(data.startYear);
            endYear.val(data.endYear);
            term.val(data.term);
            section.val(data.section);
            days.val(data.days);
            time.val(data.time);
            room.val(data.roomTitle);
            faculty.val(data.facultyName);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

//get all enrolled students
function getEnrolledStudents(offeringID) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetEnrolledStudents?offeringID=" + offeringID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            //pushed the array of student object
            arrEnrolledStudents.push(data);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

//get all assessments under the course
function getAssessments(syllabusID) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAssessmentForFormat?syllabusID=" + syllabusID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            //pushed the array of Strings (e.g. AS - 01, AS = 02, .....)
            arrAssessment.push(data);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

//get column names of student table
function getColumnNames() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetStudentColumnNames",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            //pushed the array of Strings (studentID, firstName, lastName, middleName)
            arrColumnName.push(data);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

//use this to convert to CSV
function convertToCSV (){
    
}