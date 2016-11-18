var courseID = sessionStorage.getItem("courseID");

$(document).ready(function () {
    if (posID == 7 || posID == 1) {
    } else {
        $('#edit').hide();
    }
    console.log("courseID: " + courseID);
    getSpecificCourse(courseID);
    getMapCourseToProgram(courseID);

    $('#edit').click(function () {
        sessionStorage.setItem("courseID", courseID);
        var ss = sessionStorage.getItem("courseID");
        console.log("courseID: " + ss);
    });
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
            $('#college').val(data.collegeName);
            $('#units').val(data.units);
            $('#description').val(data.description);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getMapCourseToProgram(courseID){
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificMapCourseToProgram?SelectedCourse=" + courseID,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var s = "";
            for(var x = 0; x < data.length; x++){
                if(x == (data.length - 1)){
                    console.log("entered if");
                     s = s + data[x].codeProgram;
                }else {
                     s = s + data[x].codeProgram + ", ";
                }
            }
            $('#program').val(s);
        },
        error: function (response) {
            console.log(response);
        }
    });
}