var inputs = $("#PI-inputs");
var dropDown = $("#select-PO");
var codePO = "";
var rowCount = 0;
var count = 0;
var dec = 0;
var lastCodeIGA;
var posID = sessionStorage.getItem("posID");
var userID = sessionStorage.getItem("userID");
console.log("posID: ", posID);
console.log("userID: ", userID);

$(document).ready(function () {
    if (posID == 1 || posID == 6) {
    } else {
        $('#save').hide();
        $('#addRowButton').hide();
    }
    dropDown.change(function () {
        inputs.html("");
        //inputs.remove();
        rowCount = 0;
        count = 0;
        dec = 0;
        codePO = dropDown.val();
        console.log("newcodePO: " + codePO);
        if (posID == 1 || posID == 6) {
            getAllPI(codePO);
        } else {
            function1().done(function () {
                function2().done(function () {
                });
            });
        }
        getLastPI(codePO);
    });
    $('#addRowButton').click(function () {
        dec = (dec * 1000 + 0.01 * 1000) / 1000;
        console.log("new decimal: " + dec);
        var final = dec.toString();
        final = final.replace(/^0+/, '');
        var newCodeIGA;
        if (dec === 0.1) {
            newCodeIGA = codePO + "" + final + "0";
        } else {
            newCodeIGA = codePO + "" + final;
        }
        console.log("newPI: " + newCodeIGA);
        console.log("rowCount: " + rowCount);

        var div = '<div id="inputs' + rowCount + '" >' +
                '<span id = "codePI">' + newCodeIGA + '</span>' +
                '<input class="hidden" name="codePI" value="' + newCodeIGA + '">' +
                '<div class="input-group input-group-sm">' +
                '<input name="description" id="description' + rowCount + '" type="text" class="form-control" placeholder="Add Performance Indicator" required>' +
                '<span class="input-group-btn">' +
                '<button title="edit" type="button" class="btn bg-green btn-flat"><i class="fa fa-edit"></i></button>' +
                '<button title="delete" onClick="deleteRow(' + rowCount + ')" type="button" class="btn btn-danger btn-flat"><i class="fa fa-times"></i></button>' +
                '</span>' +
                '</div>' +
                '<br>' +
                '</div>';

        inputs.append(div);
        rowCount++;
    });
});

function getLastPI(codePO) {
    console.log("entered addRow codePO: " + codePO);
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetLastPI?SelectedPO=" + codePO,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            lastCodeIGA = data;
            if (lastCodeIGA) {
                count = parseFloat(lastCodeIGA.replace(/[^0-9\.]+/g, ''));
                dec = (count % 1).toFixed(2);
            }
            console.log("lastCode: " + lastCodeIGA);
            console.log("count: " + count);
            console.log("decimal: " + dec);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getAllPI(codePO) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllMapPItoPO?codePO=" + codePO,
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


function addRow(data) {
    var codePI = data.codePI;
    var description = data.description;
    var div;

    console.log("rowCount: " + rowCount);

    if (posID == 1 || posID == 6) {
        div = '<div id="inputs' + rowCount + '" >' +
                '<span id = "codePI">' + codePI + '</span>' +
                '<input class="hidden" name="codePI" value="' + codePI + '">' +
                '<div class="input-group input-group-sm">' +
                '<input name="description" id="description' + rowCount + '" type="text" class="form-control" value="' + description + '" readOnly>' +
                '<span class="input-group-btn">' +
                '<button title="edit" type="button" class="btn bg-green btn-flat" onClick="makeRowEditable(' + rowCount + ')"><i class="fa fa-edit"></i></button>' +
                '<button title="delete" onClick="deleteRow(' + rowCount + ')" type="button" class="btn btn-danger btn-flat"><i class="fa fa-times"></i></button>' +
                '</span>' +
                '</div>' +
                '<br>' +
                '</div>';
    } else {
        div = '<div id="inputs' + rowCount + '" >' +
                '<span id = "codePI">' + codePI + '</span>' +
                '<input class="hidden" name="codePI" value="' + codePI + '">' +
                '<br>' +
                '</div>'+
                '<div>' +
                '<textarea class="form-control" width="100%" name="description" id="description' + rowCount + '" style="border: none; outline: none; resize: none; overflow:hidden" readOnly>' + description + '</textArea>' +
                '</div>';
    }
    inputs.append(div);
    rowCount++;
}

function deleteRow(num) {
    var retVal = confirm("Are you sure you want to delete this row?");
    if (retVal === true) {
        var tr = 'inputs' + num;
        document.getElementById(tr).remove();
        rowCount--;
        count--;
        return true;
    } else {
        console.log("cancelled");
        return false;
    }
}

function makeRowEditable(count) {
    var description = 'description' + count;

    if (document.getElementById(description).readOnly) {
        document.getElementById(description).readOnly = false;
    } else {
        document.getElementById(description).readOnly = true;
    }
}

function onTableLoad() {
    console.log("final rowcount ", rowCount);
    for (var x = 0; x < rowCount; x++) {
        var description = "description" + x;

        console.log("desc: ", description);
        document.getElementById(description).style.height = "1px";
        document.getElementById(description).style.height = (25 + document.getElementById(description).scrollHeight) + "px";
    }
}

function function1() {
    var dfrd1 = $.Deferred();
    setTimeout(function () {
        // doing async stuff
        getAllPI(codePO);
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