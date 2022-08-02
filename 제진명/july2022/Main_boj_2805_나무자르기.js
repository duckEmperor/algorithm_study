let fs = require('fs');
let input = fs.readFileSync('example.txt').toString().split('\n');

let [N, M] = input.shift().split(' ');

let woods = input.shift().split(' ').map(Number);

let max = Math.max(...woods);

let left = 0;
let right = max;
let ans = 0;

while (left <= right) {
    let mid = Math.floor((left+right)/2);

    let count = 0;
    for (let i = 0 ; i < N ; i++) {
        if (woods[i] - mid > 0){
            count += woods[i] - mid;
        }
    }

    // console.log(count)
    if (count < M)
        right = mid - 1;
    else if (count >= M) {
        left = mid + 1;
    }
}

console.log(right);