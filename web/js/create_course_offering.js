var syllabusID = sessionStorage.getItem("syllabusID");
var curriculum = $('#curriculum-title');
var course = $('#course-title');
var hiddenCurriculum = $('#hidden-curriculum-title');
var hiddenCourse = $('#hidden-course-title');
var hiddenSyllabus = $('#hidden-syllabusID');
var term = $('#term');
var startYear = $('#startYear');
var endYear = $('#endYear');

$(document).ready(function () {
    getSyllabus(syllabusID);
});

function getSyllabus(syllabusID) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificSyllabus?syllabusID=" + syllabusID,
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
        },
        error: function (response) {
            console.log(response);
        }
    });
}