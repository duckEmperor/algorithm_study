let fs = require('fs');
let input = fs.readFileSync('example.txt').toString().split('\n');

let readline = input[0].split(' ');

let M = parseInt(readline[0]);
let N = parseInt(readline[1]);
let K = parseInt(readline[2]);

let arr = Array.from(Array(M), () => new Array(N).fill(0));
let visited = Array.from(Array(M), () => new Array(N).fill(false));
let size = [];
const D = [[-1, 0],[1, 0],[0, 1],[0, -1]];

for (let i = 1 ; i <= K ; i++) {
  readline = input[i].split(' ');
  let x1 = parseInt(readline[0]);
  let y1 = parseInt(readline[1]);
  let x2 = parseInt(readline[2]);
  let y2 = parseInt(readline[3]);

  for (let j = y1 ; j < y2 ; j++) {
    for (let k = x1 ; k < x2 ; k++) {
      arr[j][k] = 1;
    }
  }
}

function bfs (i, j) {
  let queue = [];
  queue.push([i, j]);
  visited[i][j] = true;

  let temp = 1;

  while(queue.length != 0) {
    const [x, y] = queue.pop();

    for (let d = 0 ; d < 4 ; d++) {
      let nx = x + D[d][0];
      let ny = y + D[d][1];

      if (nx < 0 || ny < 0 || nx >= M || ny >= N || visited[nx][ny] || arr[nx][ny] === 1) continue;

      queue.push([nx, ny]);
      visited[nx][ny] = true;
      temp++;
    }
  }

  size.push(temp);
}


for (let i = 0 ; i < M ; i++) {
  for (let j = 0 ; j < N ; j++) {
    if (visited[i][j] || arr[i][j] === 1) continue;
    bfs(i, j);
  }
}

size.sort((a, b) => a - b);

console.log(`${size.length}\n`+size.join(' '));