var table = $("#data");
var typeDropDown = $(".type");
var codeCourse = sessionStorage.getItem("codeCourse");
var rowCount = 0;
var count = 0;
var AType = [];

$(document).ready(function () {
    getAllType();
    getAllAssessment(codeCourse);
    getLastAssessment(codeCourse);
    $(document).on('click', '#deleteRow', function (event) {
        $(this).closest('tr').remove();
    });

});

function getAllAssessment(course) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllAssessment?SelectedCourse=" + course,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(addRow);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getLastAssessment(course) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetLastAssessment?SelectedCourse=" + course,
        dataType: 'json',
        success: function (data) {
            console.log(data);

            var lastCodeIGA = data;
            console.log(lastCodeIGA);
            if (lastCodeIGA) {
                count = parseFloat(lastCodeIGA.replace(/[^0-9\.]+/g, ''));
            }
            console.log(count);
            $('#addRowButton').click(function () {
                var newCodeIGA;
                count += 1;
                if (count > 9) {
                    newCodeIGA = "AS-" + codeCourse + "-" + count;
                } else {
                    newCodeIGA = "AS-" + codeCourse + "-0" + count;
                }
                console.log(newCodeIGA);
                console.log("rowCount: " + rowCount);

                var tr = document.createElement("tr");
                tr.id = 'tr' + rowCount;
                var codeIGACell = document.createElement("td");
                codeIGACell.innerHTML = newCodeIGA
                        + '<input type="hidden" name="codeAssessment" class="readonlyWhite" id="codeAssessment' + rowCount + '" value="' + newCodeIGA + '" />';
                tr.appendChild(codeIGACell);

                var select = document.createElement("select");
                select.name = "AType";
                tr.appendChild(select);
                for (var x = 0; x < AType.length; x++) {
                    var option = document.createElement("option");
                    option.label = AType[x].type;
                    option.value = AType[x].typeID;
                    select.appendChild(option);
                }

                var descriptionCell = document.createElement("td");
                descriptionCell.innerHTML = '<div class="col-sm-10"><input type="text" name="description" class="form-control no-border" id="description' + rowCount + '" required></div>'
                tr.appendChild(descriptionCell);

                var weightCell = document.createElement("td");
                weightCell.innerHTML = '<div class="col-sm-10"><input type="text" class="form-control no-border" name="weight" id="weight' + rowCount + '" required></div>'
                tr.appendChild(weightCell);

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
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addRow(data) {
    var codeAssessment = data.codeAssessment;
    var typeID = data.type;
    var typeName = data.typeName;
    var description = data.description;
    var weight = data.weight;
    var status = data.status;
    var remarks = data.remarks;

    console.log("rowCount: " + rowCount);

    var tr = document.createElement("tr");
    tr.id = 'tr' + rowCount;
    var codeIGACell = document.createElement("td");
    codeIGACell.innerHTML = codeAssessment
            + '<input type="hidden" name="codeAssessment" class="readonlyWhite" id="codeAssessment' + rowCount + '" value="' + codeAssessment + '" />';
    tr.appendChild(codeIGACell);

    var typeCell = document.createElement("td");
    typeCell.innerHTML = '<div class="col-sm-10">'+typeName+'</div>'
     + '<input type="hidden" name="AType" class="readonlyWhite" id="AType' + rowCount + '" value="' + typeID + '" />';
    tr.appendChild(typeCell);
    
    var descriptionCell = document.createElement("td");
    descriptionCell.innerHTML = '<div class="col-sm-10"><input type="text" name="description" class="form-control no-border" id="description' + rowCount + '" value="' + description + '" required readOnly></div>'
    tr.appendChild(descriptionCell);

    var weightCell = document.createElement("td");
    weightCell.innerHTML = '<div class="col-sm-10"><input type="text" class="form-control no-border" name="weight" id="weight' + rowCount + '" value="' + weight + '" required readOnly></div>'
    tr.appendChild(weightCell);

    var statusCell = document.createElement("td");
    statusCell.innerHTML = '<span class="label label-success">' + status + '</span>'
            + '<input type="hidden" name="status" class="readonlyWhite" id="status' + rowCount + '" value="' + status + '" />';
    tr.appendChild(statusCell);

    var remarksCell = document.createElement("td");
    remarksCell.innerHTML = '<div class="col-sm-10"><input type="text" name="remarks" class="form-control no-border" id="remarks' + rowCount + '" value="' + remarks + '" readOnly></div>'
    tr.appendChild(remarksCell);

    var toolsCell = document.createElement("td");
    toolsCell.innerHTML = '<button type="button" id="edit' + rowCount + '" class="btn btn-success btn-xs"  onClick="makeRowEditable(' + rowCount + ')"><i class="fa fa-edit"> </i></button>' +
            '<button type="button" id="delete' + rowCount + '" class="btn btn-danger btn-xs"><i class="fa fa-trash" onClick="deleteRow(' + rowCount + ')"></i></button>';
    tr.appendChild(toolsCell);

    table.append(tr);
    rowCount++;
}

function getAllType() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllType",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                AType.push(data[x]);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addType(data) {
    var s = "<option value = " + data.typeID + ">" + data.type + "</option>";
    typeDropDown.append(s);

}


function makeRowEditable(count) {
    var description = 'description' + count;
    var weight = 'weight' + count;
    var remarks = 'remarks' + count;

    if (document.getElementById(description).readOnly) {
        document.getElementById(description).readOnly = false;
        document.getElementById(weight).readOnly = false;
        document.getElementById(remarks).readOnly = false;
    } else {
        document.getElementById(description).readOnly = true;
        document.getElementById(weight).readOnly = true;
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