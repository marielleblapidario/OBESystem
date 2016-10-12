var syllabusID = sessionStorage.getItem("syllabusID");
var curriculumID = sessionStorage.getItem("curriculumID");
var courseID = sessionStorage.getItem("courseID");
var term = sessionStorage.getItem("term");
var table = $("#data");
var arrCO = [];
var rowCount = 0;
var count = 0;

$(document).ready(function () {
    setSyllabus();
    getAllCO();
    AddAssessment();
});

function setSyllabus() {
    console.log("syllabusID: " + syllabusID);
    console.log("curriculumID: " + curriculumID);
    console.log("courseID: " + courseID);
    console.log("term: " + term);
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificSyllabus?curriculumID=" + curriculumID
                + "&courseID=" + courseID + "&term=" + term,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            $('#curriculum-title').text(data.curriculumTitle);
            $('#course-title').text(data.courseTitle);
            $('#term-title').text(data.term);
            $('#hidden-syllabusID-title').val(syllabusID);
            $('#hidden-curriculum-title').val(curriculumID);
            $('#hidden-course-title').val(courseID);
            $('#hidden-term-title').val(term);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllCO(){
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllCO?curriculumID=" + curriculumID
                + "&courseID=" + courseID + "&term=" + term,
        dataType: 'json',
        success: function (data) {
            console.log(data);
             for (var x = 0; x < data.length; x++) {
                  var co = {coID: data[x].coID, codeCO: data[x].codeCO};
                  arrCO.push(co);
             }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function AddAssessment() {
    $('#addRowButton').click(function () {
        var newCodeAT;
        count += 1;
        if (count > 9) {
            newCodeAT = "AS-" + count;
        } else {
            newCodeAT = "AS-0" + count;
        }
        console.log(newCodeAT);
        console.log("rowCount: " + rowCount);

        var tr = document.createElement("tr");
        tr.id = 'tr' + rowCount;
        var codeATCell = document.createElement("td");
        codeATCell.innerHTML = newCodeAT
                + '<input type="hidden" name="codeAT" class="readonlyWhite" id="codeAT' + rowCount + '" value="' + newCodeAT + '" />';
        tr.appendChild(codeATCell);

        var titleCell = document.createElement("td");
        titleCell.innerHTML = '<div class="col-sm-10"><input type="text" name="title" class="form-control no-border" id="title' + rowCount + '" required></div>'
        tr.appendChild(titleCell);

        var select = document.createElement("select");
        select.name = "codeCO";
        tr.appendChild(select);
        for (var x = 0; x < arrCO.length; x++) {
            var option = document.createElement("option");
            option.label = arrCO[x].codeCO;
            option.value = arrCO[x].coID;
            select.appendChild(option);
        }
        var descriptionCell = document.createElement("td");
        descriptionCell.innerHTML = '<div class="col-sm-10"><input type="text" name="description" class="form-control no-border" id="description' + rowCount + '" required></div>'
        tr.appendChild(descriptionCell);

        var weightCell = document.createElement("td");
        weightCell.innerHTML = '<div class="col-sm-10"><input type="number" name="weight" class="form-control no-border" id="weight' + rowCount + '" required></div>'
        tr.appendChild(weightCell);

        var toolsCell = document.createElement("td");
        toolsCell.innerHTML = '<button type="button" id="edit' + rowCount + '" class="btn btn-success btn-xs"  onClick="makeRowEditable(' + rowCount + ')"><i class="fa fa-edit"> </i></button>' +
                '<button type="button" id="delete' + rowCount + '" class="btn btn-danger btn-xs"><i class="fa fa-trash" onClick="deleteRow(' + rowCount + ')"></i></button>';
        tr.appendChild(toolsCell);

        table.append(tr);
        rowCount++;
    });
}

function makeRowEditable(count) {
    var description = 'description' + count;
    var title = 'title' + count;

    if (document.getElementById(description).readOnly) {
        document.getElementById(description).readOnly = false;
        document.getElementById(title).readOnly = false;
    } else {
        document.getElementById(description).readOnly = true;
        document.getElementById(title).readOnly = true;
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