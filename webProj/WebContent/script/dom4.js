var ps = document.querySelector('div>p');
ps.textContent = 'hello';
ps.style.backgroundColor = 'yellow'; 
// ps.forEach(function(val){
//     console.log(val);
//     val.textContent = '<h1>hello</h1>';
//     val.style.backgroundColor = 'green';
// });


var numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
//filter, map, forEach => filter: 짝수만 걸러내도록evenVal;담기
//evenVal => n *3; => mapVal;
//mapVal = console.log(각각 출력)

var evenVal = numbers.filter(function (val, idx, ary) {
    return val % 2 == 0;
})

var mapVal = evenVal.map(function (val, idx, ary) {
    return val * 3;
})

mapVal.forEach(function (val, idx, ary) {
    console.log(val);
})
// => arrow function.
var sum = (a, b) => a + b;
sum(10,20);

