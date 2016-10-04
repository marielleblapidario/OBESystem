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
    var x = courseList.indexOf(selectedCourse);
    courseList.splice(x,1);
//    courseList = jQuery.grep(courseList, function (value) {
//        return value !== selectedCourse;
//    });
    console.log("after: " + courseList.length);
    showCourses();
    if (list.length > 0) {
        $.ajax({
            type: "GET",
            url: "/OBESystem/GetCourseByCode?SelectedCourse=" + selectedCourse,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                var code = data.codeCourse 
                        + '<input type="hidden" name="codeCourse" class="readonlyWhite" value="' + data.codeCourse + '" />' ;
                var title = data.title 
                        + '<input type="hidden" name="title" class="readonlyWhite"  value="' + data.title + '" />';
                var units = data.units 
                        + '<input type="hidden" name="units" class="readonlyWhite" value="' + data.units + '" />';
                var tool = "<button type=\"button\" id=\"deleteRow\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash\"></i></button>";

                table.row.add([code, title, units, tool]);
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
