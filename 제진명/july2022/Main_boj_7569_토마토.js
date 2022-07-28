let fs = require('fs');
let input = fs.readFileSync('example.txt').toString();

let readline = input.split(`\n`);

let lineSplit = readline[0].split(' ');

let M = parseInt(lineSplit[0]);
let N = parseInt(lineSplit[1]);
let H = parseInt(lineSplit[2]);

let tomatoBox = Array.from(Array(H), () => Array.from(Array(N), () => new Array(M).fill(0)));

let queue = [];
let visited = Array.from(Array(H), () => Array.from(Array(N), () => new Array(M).fill(false)));

let amount = 0;

for (let i = 0 ; i < H ; i++) {
    for (let j = 0 ; j < N ; j++) {
        lineSplit = readline[j+i*N+1].split(' ');
        for (let k = 0 ; k < M ; k++) {
            tomatoBox[i][j][k] = parseInt(lineSplit[k]);
            if (tomatoBox[i][j][k] === 1) {
                queue.push([i, j, k, 0]);
                visited[i][j][k] = true;
            }

            if (tomatoBox[i][j][k] !== -1) {
                amount += 1;
            }
        }
    }
}

let D = [[0,0,1],[0,0,-1],[-1,0,0],[1,0,0],[0,-1,0],[0,1,0]];

let ans = 0;
let count = queue.length;

while(queue.length !== 0) {
    const [x, y, z, day] = queue.pop();
    if (ans <= day) ans = day;

    for (let d = 0 ; d < 6 ; d++) {
        const nx = x + D[d][0];
        const ny = y + D[d][1];
        const nz = z + D[d][2];

        if (nx < 0 || ny < 0 || nz < 0 || nx >= H || ny >= N || nz >= M || visited[nx][ny][nz] || tomatoBox[nx][ny][nz] === -1) continue;

        queue.unshift([nx, ny, nz, day+1]);
        count += 1;
        visited[nx][ny][nz] = true;
    }
}

if (amount !== count) console.log(-1)
else console.log(ans);