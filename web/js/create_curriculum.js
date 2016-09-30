var list = [];
var courseList = [];
var table = $("#example1").DataTable();
var dropDownCourse = $("#select-course");

$(document).ready(function () {
    getAllCourse();
    $("#table").hide();
});

$("#button-add").click(function () {
    var selectedCourse = $("#select-course option:selected").val();
    console.log("selected:" + selectedCourse);
    list.push(selectedCourse);
    console.log("before: " + courseList.length);
    courseList = jQuery.grep(courseList, function(value){
        return value != selectedCourse;
    });
     console.log("after: " + courseList.length);
    showCourses();
    if (list.length > 0) {
        $.ajax({
            type: "GET",
            url: "/OBESystem/GetCourseByCode?SelectedCourse=" + selectedCourse,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                var code = data.codeCourse;
                var title = data.title;
                var units = data.units;

                table.row.add([code, title, units]);
                table.draw();
            },
            error: function (response) {
                console.log(response);
            }
        });

        $("#table").show();
    } else {
        $("#table").hide();
    }

});

function getAllCourse() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllCourse",
        dataType: 'json',
        success: function (data) {

            console.log(data);
            for (var x = 0; x < data.length; x++) {
                var course = {codeCourse: data[x].codeCourse, title: data[x].title};
                courseList.push(course);

            }
            showCourses();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function showCourses() {
    dropDownCourse.find('option').remove().end();
    for (var x = 0; x < courseList.length; x++) {
        var s = "<option value=" + courseList[x].codeCourse + ">" + courseList[x].title + "</option>";
        dropDownCourse.append(s);
    }
}

function addRow(data) {
    var code = data.codeCourse;
    var title = data.title;
    var units = data.units;

    dataTable.row.add(code, title, units);
    table.draw();
}