var dropDownCourse = $("#select-course");
var curriculumDropDown = $("#select-curriculum");
var termDropDown = $("#select-term");
var table = $("#data");
var courseList = [];
var arrPI = [];
var rowCount = 0;
var count = 0;
var curriculumID;
var courseID;
var term;

$(document).ready(function () {
    getAllCurriculum();
    curriculumDropDown.change(function () {
        courseList = [];
        curriculumID = curriculumDropDown.val();
        getAllCourse(curriculumID);
        arrPI = [];
    });
    dropDownCourse.change(function () {
        courseID = dropDownCourse.val();
        arrPI = [];
        getAllPI(curriculumID, courseID);
    });
    termDropDown.change(function () {
        term = termDropDown.val;
    });
    addCO();
});

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


function getAllCurriculum() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllCurriculum",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var a = "<option disabled selected value> -- select an option -- </option>";
            curriculumDropDown.append(a);
            data.forEach(showCurriculums);
        },
        error: function (response) {
            console.log(response);
        }
    });
}
function getAllCourse(SelectedCurriculum) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificMapCurriculumToCourse?SelectedCurriculum=" + SelectedCurriculum,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                var course = {courseID: data[x].courseID, courseTitle: data[x].courseTitle};
                courseList.push(course);

            }
            showCourses();
        },
        error: function (response) {
            console.log(response);
        }
    });
}
function addCO() {
    $('#addRowButton').click(function () {
        var newCodeCO;
        count += 1;
        if (count > 9) {
            newCodeCO = "CO-" + count;
        } else {
            newCodeCO = "CO-0" + count;
        }
        console.log(newCodeCO);
        console.log("rowCount: " + rowCount);

        var tr = document.createElement("tr");
        tr.id = 'tr' + rowCount;
        var codeCOCell = document.createElement("td");
        codeCOCell.innerHTML = newCodeCO
                + '<input type="hidden" name="codeCO" class="readonlyWhite" id="codeCO' + rowCount + '" value="' + newCodeCO + '" />';
        tr.appendChild(codeCOCell);

        var descriptionCell = document.createElement("td");
        descriptionCell.innerHTML = '<div class="col-sm-10"><input type="text" name="description" class="form-control no-border" id="description' + rowCount + '" required></div>'
        tr.appendChild(descriptionCell);

        var select = document.createElement("select");
        select.name = "codePI";
        tr.appendChild(select);
        for (var x = 0; x < arrPI.length; x++) {
            var option = document.createElement("option");
            option.label = arrPI[x].codePI;
            option.value = arrPI[x].codePI;
            select.appendChild(option);
        }

        var statusCell = document.createElement("td");
        statusCell.innerHTML = '<span class="label label-success">pending</span>'
                + '<input type="hidden" name="status" class="readonlyWhite" id="status' + rowCount + '" value="pending" />';
        tr.appendChild(statusCell);

        var remarksCell = document.createElement("td");
        remarksCell.innerHTML = '<div class="col-sm-10"><input type="text" name="remarks" class="form-control no-border" id="remarks' + rowCount + '"></div>'
        tr.appendChild(remarksCell);

        var toolsCell = document.createElement("td");
        toolsCell.innerHTML = '<button type="button" id="edit' + rowCount + '" class="btn btn-success btn-xs"  onClick="makeRowEditable(' + rowCount + ')"><i class="fa fa-edit"> </i></button>' +
                '<button type="button" id="delete' + rowCount + '" class="btn btn-danger btn-xs"><i class="fa fa-trash" onClick="deleteRow(' + rowCount + ')"></i></button>';
        tr.appendChild(toolsCell);

        table.append(tr);
        rowCount++;
    });
}
function showCourses() {
    dropDownCourse.find('option').remove().end();
    var a = "<option disabled selected value> -- select an option -- </option>";
    dropDownCourse.append(a);
    for (var x = 0; x < courseList.length; x++) {
        var s = "<option value=" + courseList[x].courseID + ">" + courseList[x].courseTitle + "</option>";
        dropDownCourse.append(s);
    }
}

function showCurriculums(data) {
    var s = "<option value = " + data.codeCurriculum + ">" + data.title + "</option>";
    curriculumDropDown.append(s);
}

function makeRowEditable(count) {
    var description = 'description' + count;
    var remarks = 'remarks' + count;

    if (document.getElementById(description).readOnly) {
        document.getElementById(description).readOnly = false;
        document.getElementById(remarks).readOnly = false;
    } else {
        document.getElementById(description).readOnly = true;
        document.getElementById(remarks).readOnly = true;
    }
}

function deleteRow(num) {
    var retVal = confirm("Are you sure you want to delete this row?");
    if (retVal === true) {
        var tr = 'tr' + num;
        document.getElementById(tr).remove();
        rowCount--;
        count--;
        return true;
    } else {
        console.log("cancelled");
        return false;
    }
}