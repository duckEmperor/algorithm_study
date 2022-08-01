let fs = require('fs');
let input = fs.readFileSync('example.txt').toString().split('\n');

const D = [[0,0,1],[0,0,-1],[-1,0,0],[1,0,0],[0,-1,0],[0,1,0]];

const [M,N,H] = input.shift().split(' ').map(Number);

let queue = [];
let visited = Array.from(Array(H), () => Array.from(Array(N), () => new Array(M).fill(false)));

let count = M*N*H;
let z = 0;
let ans;

for (let i = 0 ; i < input.length ; i++) {
    let box = input[i].split(' ').map(Number);
    box.forEach((tomato, pos) => {
        if (tomato === 1) {
            queue.push([i % N, pos, z, 0]);
            visited[z][i % N][pos] = true;
            count--;
        } else if (tomato === -1) {
            visited[z][i % N][pos] = 1;
            count--;
        }
    });

    if((i+1) % N === 0) ++z;
}

let idx = 0;

while (queue.length != idx) {
    const [x, y, z, pos] = queue[idx];
    for (let d = 0 ; d < D.length ; d++) {
        const nx = x + D[d][0];
        const ny = y + D[d][1];
        const nz = z + D[d][2];

        if (nx < 0 || ny < 0 || nz < 0 || nx >= N || ny >= M || nz >= H) continue;

        if (!visited[nz][nx][ny]) {
            visited[nz][nx][ny] = true;
            queue.push([nx, ny, nz, pos+1]);
            count--;
        }
    }

    idx++;
    ans = pos;
}

console.log(count ? -1 : ans);