let fs = require('fs');
let input = fs.readFileSync('example.txt').toString().split(' ');

let N = parseInt(input[0]);
let K = parseInt(input[1]);

if (N <= K) {
    console.log(0);
    return;
}

let buy = 0;

while(true) {
    let count = 0;
    let newN = N;

    while (newN != 0) {
        if (newN % 2 === 1) {
            count += 1;
        }
        newN = Math.floor(newN/2);
    }
    if (count <= K) break;
    N += 1;
    buy += 1;
}

console.log(buy)