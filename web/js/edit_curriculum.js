var codeCurriculum = sessionStorage.getItem("codeCurriculum");
var dropDownCourse = $("#select-course");
var table = $("#data");
var spanUnits = $("#total-units");
var list = [];
var courseList = [];
var arrPreRequisite = [];
var arrTerm = [];
var arrYearLevel = [];
var rowCount = 0;
var totalUnits = 0;
var program;

$(document).ready(function () {
    function1().done(function () {
        function2().done(function () {
        });
    });

    $("#button-add").click(function () {
        var selectedCourse = $("#select-course option:selected").val();
        list.push(selectedCourse);

        for (var i = 0; i < courseList.length; i++) {
            console.log("for loop: " + courseList[i].courseID + " compare to " + selectedCourse);
            if (courseList[i].courseID == selectedCourse) {
                courseList.splice(i, 1);
                break;
            }
        }
        showCourses();
        if (list.length > 0) {
            $.ajax({
                type: "GET",
                url: "/OBESystem/GetCourseByCode?SelectedCourse=" + selectedCourse,
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    console.log("rowCount: " + rowCount);

                    var rowCourse = {count: rowCount, courseID: data.courseID, title: data.title, units: data.units};
                    arrRowCount.push(rowCourse);

                    var tr = '';
                    var openingTr = '<tr id =tr' + rowCount + '>';
                    tr += openingTr;

                    var code = '<td>' + data.codeCourse
                            + '<input type="hidden" name="codeCourse" class="readonlyWhite" value="' + data.codeCourse + '" />'
                            + '<input type="hidden" name="courseID" class="readonlyWhite" value="' + data.courseID + '" />'
                            + '</td>';
                    tr += code;

                    var title = '<td>' + data.title
                            + '<input type="hidden" name="title" class="readonlyWhite"  value="' + data.title + '" />'
                            + '</td>';
                    tr += title;

                    var units = '<td>' + data.units
                            + '<input type="hidden" name="units" class="readonlyWhite" value="' + data.units + '" />'
                            + '</td>';
                    tr += units;

                    var OYS = '<td><select name="yearLevel" class="form-control">';
                    tr += OYS;
                    for (var x = 0; x < arrYearLevel.length; x++) {
                        var s = "<option value=" + arrYearLevel[x].yearLevel + ">" + arrYearLevel[x].yearLevel + "</option>";
                        tr += s;
                    }
                    var CYS = '</select></td>';
                    tr += CYS;

                    var OTS = '<td><select name="term" class="form-control">';
                    tr += OTS;
                    for (var x = 0; x < arrTerm.length; x++) {
                        var s = "<option value=" + arrTerm[x].term + ">" + arrTerm[x].term + "</option>";
                        tr += s;
                    }
                    var CTS = '</select></td>';
                    tr += CTS;

                    console.log("preRequisite size: " + arrPreRequisite.length);

                    var OPS = '<td><select name="prerequisite" class="form-control">';
                    tr += OPS;
                    var a = "<option selected value=\"-1\"> -- select an option -- </option>";
                    tr += a;
                    for (var x = 0; x < arrPreRequisite.length; x++) {
                        var s = "<option value=" + arrPreRequisite[x].courseID + ">" + arrPreRequisite[x].title + "</option>";
                        tr += s;
                    }
                    var CPS = '</select></td>';
                    tr += CPS;

                    var tool = '<td><button title="delete" type="button" id="delete' + rowCount + '" class="btn btn-danger btn-xs"><i class="fa fa-trash" onClick="deleteRow(' + rowCount + ')"></i></button></td>';
                    tr += tool;

                    var closingTr = '</tr>';
                    tr += closingTr;

                    table.append(tr);
                    rowCount++;

                    totalUnits = totalUnits + parseInt(data.units);
                    spanUnits.text(totalUnits);
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
            $("#from").val(data.startYear);
            $("#to").val(data.endYear);
            $("#description").val(data.description);
            program = data.program;
            getAllCourse(program);
            getAllTerm();
            getAllYearLevel();


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
            for (var a = 0; a < data.length; a++) {
                addRow(data[a]);
                list.push();
            }
            //remove existing courses inside courseList
            for (var i = courseList.length - 1; i >= 0; i--) {
                for (var j = 0; j < data.length; j++) {
                    if (courseList[i].courseID == data[j].courseID) {
                        courseList.splice(i, 1);
                    }
                }
            }
            showCourses();
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
    
    totalUnits = totalUnits + parseInt(units);

    var appendTr =
            '<tr id =tr' + rowCount + '>' +
            '<td>' + data.codeCourse
            + '<input type="hidden" name="codeCourse" class="readonlyWhite" value="' + data.codeCourse + '" />'
            + '<input type="hidden" name="courseID" class="readonlyWhite" value="' + data.courseID + '" />'
            + '</td>'
            + '<td>' + data.title
            + '<input type="hidden" name="title" class="readonlyWhite"  value="' + data.title + '" />'
            + '</td>'
            + '<td>' + units
            + '<input type="hidden" name="title" class="readonlyWhite"  value="' + units + '" />'
            + '</td>'
            + '<td><select name="yearLevel" class="form-control">';
    for (var x = 0; x < arrYearLevel.lenth; x++) {
        if (arrYearLevel[x].yearLevel == yearLevel) {
            appendTr += '<option value=' + arrYearLevel[x].yearLevel + ' selected>' + arrYearLevel[x].yearLevel + '</option>';
        } else {
            appendTr += '<option value=' + arrYearLevel[x].yearLevel + '>' + arrYearLevel[x].yearLevel + '</option>';
        }
    }
    appendTr += '</select></td>';
    appendTr += '<td><select name="term" class="form-control">';
    for (var x = 0; x < arrTerm.length; x++) {
        if (arrTerm[x.term] == term) {
            appendTr += '<option value=' + arrTerm[x].term + ' selected>' + arrTerm[x].term + '</option>';
        } else {
            appendTr += '<option value=' + arrTerm[x].term + '>' + arrTerm[x].term + '</option>';
        }
    }
    appendTr += '</select></td>';
    check = false;
    appendTr += '<td><select name="prerequisite" class="form-control">';
    for (var x = 0; x < arrPreRequisite.length; x++) {
        if (arrPreRequisite[x].courseID == preRequisite) {
            appendTr += '<option value=' + arrPreRequisite[x].courseID + ' selected>' + arrPreRequisite[x].title + '</option>';
        } else {
            appendTr += '<option value=' + arrPreRequisite[x].courseID + '>' + arrPreRequisite[x].title + '</option>';
        }
    }
    appendTr += '</select></td>';
    appendTr += '</tr>';
    table.append(appendTr);
    rowCount++;
}

function getAllCourse(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetCoursesOnProgram?SelectedProgram=" + program,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                var course = {courseID: data[x].courseID, title: data[x].codeCourse};
                courseList.push(course);
                arrPreRequisite.push(course);

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
            for (var x = 0; x < data.length; x++) {
                arrTerm.push(data[x]);
            }
            showCourses();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllYearLevel() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllYearLevel",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrYearLevel.push(data[x]);
            }
            showCourses();
        },
        error: function (response) {
            console.log(response);
        }
    }
    );
}

function showCourses() {
    dropDownCourse.find('option').remove().end();
    var a = "<option disabled selected value> -- select an option -- </option>";
    dropDownCourse.append(a);
    for (var x = 0; x < courseList.length; x++) {
        var s = "<option value=" + courseList[x].courseID + ">" + courseList[x].title + "</option>";
        dropDownCourse.append(s);
    }
}

function function1() {
    var dfrd1 = $.Deferred();
    setTimeout(function () {
        // doing async stuff
        getSpecificCurriculum(codeCurriculum);
        console.log('task 1 in function1 is done!');
        dfrd1.resolve();
    }, 1000);

    return dfrd1.promise();
}

function function2() {
    var dfrd1 = $.Deferred();
    setTimeout(function () {
        // doing async stuff
        getSpecificMapCurriculumToCourse(codeCurriculum);
        console.log('task 1 in function2 is done!');
        dfrd1.resolve();
    }, 2000);
    return dfrd1.promise();
}