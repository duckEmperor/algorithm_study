let fs = require('fs');
let input = fs.readFileSync('example.txt').toString().split('\n');

let [R, C] = input.shift().split(' ').map(Number);

let field = Array.from(Array(R), () => new Array(C));
let visited = Array.from(Array(R), () => new Array(C).fill(false));

let jx = null;
let jy = null;
let fx = null;
let fy = null;

for (let i = 0 ; i < R ; i++) {
    const readline = input.shift().split('').map(String);
    for (let j = 0 ; j < readline.length ; j++) {
        if (readline[j] === 'J'){
            jx = i;
            jy = j;
        } else if (readline[j] === 'F') {
            fx = i;
            fy = j;
        }
        field[i][j] = readline[j];
    }
}