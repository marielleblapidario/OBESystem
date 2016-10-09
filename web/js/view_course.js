var codeCourse = sessionStorage.getItem("codeCourse");

$(document).ready(function () {
    console.log("codeCourse: " + codeCourse);
    getSpecificCourse(codeCourse);

    $('#edit').click(function () {
        sessionStorage.setItem("codeCourse", codeCourse);
        var ss = sessionStorage.getItem("codeCourse");
        console.log("codeCourse: " + ss);
    });
});


function getSpecificCourse(course) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificCourse?SelectedCourse=" + course,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            $('#title').val(data.title);
            $('#codeProgram').val(data.codeProgram);
            $('#college').val(data.collegeName);
            $('#units').val(data.units);
            $('#description').val(data.description);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getMapCourseToProgram(){
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificCourse?SelectedCourse=" + course,
        dataType: 'json',
        success: function (data) {
            console.log(data);
        },
        error: function (response) {
            console.log(response);
        }
    });
}