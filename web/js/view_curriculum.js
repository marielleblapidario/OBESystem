var codeCurriculum = sessionStorage.getItem("codeCurriculum");
var table = $("#example1").DataTable();

$(document).ready(function () {
    $("#table").hide();
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
            var range = data.startYear+" - "+ data.endYear;
            $("#range").val(range);
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
            $("#table").show();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addRow(data) {
    var codeCourse = data.codeCourse;
    var courseTitle = data.courseTitle;
    var units = data.units;

    table.row.add([codeCourse, courseTitle, units]);
    table.draw();

}