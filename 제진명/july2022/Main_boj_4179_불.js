const fs = require('fs');
const input = fs.readFileSync('./dev/stdin.txt').toString().split('\n');

const [R, C] = input.shift().split(' ').map(Number);

const D = [[0,1],[0,-1],[1,0],[-1,0]];

let field = Array.from(Array(R), () => new Array(C));
let visited = Array.from(Array(R), () => new Array(C).fill(false));

let jx = null;
let jy = null;

let queue = [];
let ans = 987654321;

for (let i = 0 ; i < R ; i++) {
    const readline = input.shift().split('').map(String);
    for (let j = 0 ; j < readline.length ; j++) {
        if (readline[j] === 'J'){
            jx = i;
            jy = j;
            field[i][j] = '.';
            visited[i][j] = true;
            continue;
        } else if (readline[j] === 'F') {
            queue.push([i, j]);
        }
        field[i][j] = readline[j];
    }
}

backTracking(jx, jy, 0, 0, queue);
if (ans === 987654321) console.log("IMPOSSIBLE");
else console.log(ans);

function backTracking (x, y, cnt, idx, queue) {
    if (x >= R || y >= C || x < 0 || y < 0) {
        ans = Math.min(ans, cnt);
        return;
    }

    const lenQueue = queue.length;
    let _idx = idx;


    //불을 먼저 번지게 함
    for (let i = idx ; i < lenQueue ; i++) {
        const [cfx, cfy] = queue[i];
        for (let d = 0 ; d < 4 ; d++) {
            const nfx = cfx + D[d][0];
            const nfy = cfy + D[d][1];

            if (nfx < 0 || nfy < 0 || nfx >= R || nfy >= C || field[nfx][nfy] === '#' || field[nfx][nfy] === 'F') continue;

            field[nfx][nfy] = 'F'
            queue.push([nfx, nfy]);
        }
        _idx++;
    }

    //사람 이동 : 불이 안붙은 위치만 이동 가능, 벽이 아닌 부분만 이동 가능
    for (let d = 0 ; d < 4 ; d++) {
        const njx = x + D[d][0];
        const njy = y + D[d][1];

        if (njx < 0 || njy < 0 || njx >= R || njy >= C) {
        }
        else if (field[njx][njy] === '#' || field[njx][njy] === 'F' || visited[njx][njy]) continue;

        if (!(njx < 0 || njy < 0 || njx >= R || njy >= C)) {
            visited[njx][njy] = true;
        }
        backTracking(njx, njy, cnt + 1, _idx, queue);
        if (!(njx < 0 || njy < 0 || njx >= R || njy >= C)) {
            visited[njx][njy] = false;
        }
    }

    //리턴하면 번지게한 불 다시 돌려놓기
    for (let i = queue.length - 1 ; i >= lenQueue ; i--) {
        const [cfx, cfy] = queue.pop();
        field[cfx][cfy] = '.';
    }
}