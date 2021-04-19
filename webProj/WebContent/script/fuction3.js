//function.js

var sum = function (a, b) {
    var num1 = 10;
    var num2 = 20;
    console.log(a + (num1 + num2) + b);
}
sum("결과는: ", " 입니다.");
// console.log(sum);

var factoral = function fac(n) {

    if (n == 1) {
        return 1;

    }
    return n * fac(n - 1);

}

var result = factoral(3);
console.log(result);

function sumVal(a, b) {
    return a + b;
}

function mulVal(a, b) {
    return a * b;
}

function executeFunc(callBack, num1, num2) {
    var result = callBack(num1, num2);
    return result;
}

result = executeFunc(mulVal, 3, 5);
console.log('결과 : ' + result);