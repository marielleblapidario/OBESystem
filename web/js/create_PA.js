var table = $("#data");
var programCode = sessionStorage.getItem("programCode");
var rowCount = 0;
var count = 0;
var arrIGA = [];

$(document).ready(function () {
    getAllIGA();
    getAllPA(programCode);
    getLastPA(programCode);
});

function getAllPA(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllPA?SelectedProgram=" + program,
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

function getAllIGA() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllIGA",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrIGA.push(data[x]);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getLastPA(program) {

    $.ajax({
        type: "GET",
        url: "/OBESystem/GetLastPA?SelectedProgram=" + program,
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
                    newCodeIGA = "PA-" + programCode + "-" + count;
                } else {
                    newCodeIGA = "PA-" + programCode + "-0" + count;
                }
                console.log(newCodeIGA);
                console.log("rowCount: " + rowCount);

                var tr = document.createElement("tr");
                tr.id = 'tr' + rowCount;
                var codeIGACell = document.createElement("td");
                codeIGACell.innerHTML = newCodeIGA
                        + '<input type="hidden" name="codePA" class="readonlyWhite" id="codePA' + rowCount + '" value="' + newCodeIGA + '" />';
                tr.appendChild(codeIGACell);

                var descriptionCell = document.createElement("td");
                descriptionCell.innerHTML = '<div class="col-sm-10"><input type="text" name="description" class="form-control no-border" id="description' + rowCount + '" required></div>'
                tr.appendChild(descriptionCell);

                var select = document.createElement("select");
                select.name = "mapIGA";
                tr.appendChild(select);
                for (var x = 0; x < arrIGA.length; x++) {
                    var option = document.createElement("option");
                    option.label = arrIGA[x].title;
                    option.value = arrIGA[x].codeIGA;
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
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function addRow(data) {
    var codePA = data.codePA;
    var description = data.description;
    var codeIGA = data.codeIGA;
    var igaTitle = data.igaTitle;
    var status = data.status;
    var remarks = data.remarks;

    console.log("rowCount: " + rowCount);

    var tr = document.createElement("tr");
    tr.id = 'tr' + rowCount;
    var codeIGACell = document.createElement("td");
    codeIGACell.innerHTML = codePA
            + '<input type="hidden" name="codePA" class="readonlyWhite" id="codePA' + rowCount + '" value="' + codePA + '" />';
    tr.appendChild(codeIGACell);

    var descriptionCell = document.createElement("td");
    descriptionCell.innerHTML = '<div class="col-sm-10"><input type="text" name="description" class="form-control no-border" id="description' + rowCount + '" value="' + description + '" required readOnly></div>'
    tr.appendChild(descriptionCell);

    var mapIGACell = document.createElement("td");
    mapIGACell.innerHTML = '<div class="col-sm-10"><input type="text" class="form-control no-border" value="' + igaTitle + '" required readOnly></div>'
     + '<input type="hidden" name="mapIGA" class="readonlyWhite" id="mapIGA' + rowCount + '" value="' + codeIGA + '" />';
    tr.appendChild(mapIGACell);

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