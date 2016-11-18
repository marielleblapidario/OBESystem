var table = $("#data");
var programCode = sessionStorage.getItem("programCode");
var rowCount = 0;
var count = 0;
var arrIGA = [];
var posID = sessionStorage.getItem("posID");
var userID = sessionStorage.getItem("userID");
console.log("posID: ", posID);
console.log("userID: ", userID);

$(document).ready(function () {
    getAllIGA();
    function1().done(function () {
        function2().done(function () {
        });
    });
    //getAllPA(programCode);
    getLastPA(programCode);
    if(posID == 1 || posID == 6){} else {
        $('#save').hide();
        $('#addRowButton').hide();
    }
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
                descriptionCell.innerHTML = '<div class="col-sm-10"><textarea onkeyup="textAreaAdjust(this)" style="border: none; outline: none;  resize: none; overflow:hidden" name="description" class="form-control no-border" id="description' + rowCount + '" required></textarea></div>'
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

                var remarksCell = document.createElement("td");
                remarksCell.innerHTML = '<div class="col-sm-10"><textarea onkeyup="textAreaAdjust(this)" style="border: none; outline: none;  resize: none; overflow:hidden" name="remarks" class="form-control no-border" id="remarks' + rowCount + '"></textarea></div>'
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
    var remarks = data.remarks;

    console.log("rowCount: " + rowCount);

    var tr = document.createElement("tr");
    tr.id = 'tr' + rowCount;
    var codeIGACell = document.createElement("td");
    codeIGACell.innerHTML = codePA
            + '<input type="hidden" name="codePA" class="readonlyWhite" id="codePA' + rowCount + '" value="' + codePA + '" />';
    tr.appendChild(codeIGACell);

    var descriptionCell = document.createElement("td");
    descriptionCell.innerHTML = '<div class="col-sm-10"><textarea onkeyup="textAreaAdjust(this)" style="border: none; outline: none;  resize: none; overflow:hidden" name="description" class="form-control no-border" id="description' + rowCount + '" required readOnly>' + description + '</textarea></div>';
    tr.appendChild(descriptionCell);

    var mapIGACell = document.createElement("td");
    mapIGACell.innerHTML = '<div class="col-sm-10"><textAreaAdjust(this)" style="border: none; outline: none;  resize: none; overflow:hidden" id="mapIGA' + rowCount + '" class="form-control no-border" required readOnly>' + igaTitle + '</textarea></div>'
     + '<input type="hidden" name="mapIGA" class="readonlyWhite" value="' + codeIGA + '" />';
    tr.appendChild(mapIGACell);

    var remarksCell = document.createElement("td");
    remarksCell.innerHTML = '<div class="col-sm-10"><textarea onkeyup="textAreaAdjust(this)" style="border: none; outline: none;  resize: none; overflow:hidden" name="remarks" class="form-control no-border" id="remarks' + rowCount + '" readOnly>' + remarks + '</textarea></div>';
    tr.appendChild(remarksCell);

    var toolsCell = document.createElement("td");
    if(posID == 1 || posID == 6){
        toolsCell.innerHTML = '<button type="button" id="edit' + rowCount + '" class="btn btn-success btn-xs"  onClick="makeRowEditable(' + rowCount + ')"><i class="fa fa-edit"> </i></button>' +
            '<button type="button" id="delete' + rowCount + '" class="btn btn-danger btn-xs"><i class="fa fa-trash" onClick="deleteRow(' + rowCount + ')"></i></button>';
    } else {
        toolsCell.innerHTML = '';
    }
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

function textAreaAdjust(o) {
  o.style.height = "1px";
  o.style.height = (25+o.scrollHeight)+"px";
}

function onTableLoad() {
    console.log("final rowcount ", rowCount);
    for (var x = 0; x < rowCount; x++) {
        var description = "description" + x;
        var remarks = "remarks" + x;
        var mapIGA = "mapIGA" + x;
        
        console.log("desc: ", description);
        console.log("remark: ", remarks);
        document.getElementById(description).style.height = "1px";
        document.getElementById(description).style.height = (25 + document.getElementById(description).scrollHeight) + "px";
        document.getElementById(remarks).style.height = "1px";
        document.getElementById(remarks).style.height = (25 + document.getElementById(remarks).scrollHeight) + "px";
        document.getElementById(mapIGA).style.height = "1px";
        document.getElementById(mapIGA).style.height = (25 + document.getElementById(mapIGA).scrollHeight) + "px";
    }
}

function function1() {
    var dfrd1 = $.Deferred();
    setTimeout(function () {
        // doing async stuff
       getAllPA(programCode);
        console.log('task 1 in function1 is done!');
        dfrd1.resolve();
    }, 1000);

    return dfrd1.promise();
}

function function2() {
    var dfrd1 = $.Deferred();
    setTimeout(function () {
        // doing async stuff
        onTableLoad();
        console.log('task 1 in function2 is done!');
        dfrd1.resolve();
    }, 2000);
    return dfrd1.promise();
}