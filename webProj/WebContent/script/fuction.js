//fuction.js
function sum(num3, num4) {
    var num1 = 10;
    var num2 = 20;
    console.log(num1 + num2 + num3 + num4);
    return (num1 + num2 + num3 + num4);
}

// var result = sum(30, 40);
var numAry = [3, 5, 1, 8, 9];

function arySum(ary) {
    var sum = 0;
    for (i = 0; i < numAry.length; ++i) {
        if (ary[i] % 2 == 1) {
            sum += ary[i];
        }

    }
    return sum;
}


var result = arySum(numAry);
document.write('결과값은: ' + result);

function multi(grade) {
    var tbl = '<table border="1">';
    for (var i=1; i<10; i++) {
        tbl +='<tr><td>' + grade + '*' + i +
        '</td><td>=</td><td>' + 
        (grade*i) + '</td></tr>';
    }
    tbl+='</table>';
    document.write(tbl);
}
multl(9);