let fs = require('fs');
class Heap {

    constructor() {
        this.heap = []
    }

    getLeftChildIndex = (parentIndex) => parentIndex * 2 + 1;
    getRightChildIndex = (parentIndex) => parentIndex * 2 + 2;
    getParentIndex = (childIndex) => Math.floor((childIndex - 1) / 2);

    peek = () => this.heap[0];

    insert = (key, value) => {
        const node = { key, value };
        this.heap.push(node);
        this.heapifyUp()
    }

    remove = () => {
        const count = this.heap.length;
        const rootNode = this.heap[0];

        if (count <= 0) return undefined;
        if (count === 1) this.heap = [];
        else {
            this.heap[0] = this.heap.pop();
            this.heapifyDown();
        }

        return rootNode;
    }

    heapifyUp = () => {
        let index = this.heap.length - 1;
        const lastInsertedNode = this.heap[index];

        while (index > 0) {
            const parentIndex = this.getParentIndex(index);

            if(this.heap[parentIndex].key > lastInsertedNode.key) {
                this.heap[index] = this.heap[parentIndex];
                index = parentIndex;
            } else break;
        }

        this.heap[index] = lastInsertedNode;
    }

    heapifyDown = () => {
        let index = 0;
        const count = this.heap.length;
        const rootNode = this.heap[index];

        while (this.getLeftChildIndex(index) < count) {
            const leftChildIndex = this.getLeftChildIndex(index);
            const rightChildIndex = this.getRightChildIndex(index);

            const smallerChildIndex = rightChildIndex < count && this.heap[rightChildIndex].key < this.heap[leftChildIndex].key ? rightChildIndex : leftChildIndex;

            if (this.heap[smallerChildIndex].key <= rootNode.key) {
                this.heap[index] = this.heap[smallerChildIndex];
                index = smallerChildIndex
            } else break;
        }

        this.heap[index] = rootNode
    }
}

class PriorityQeuue extends Heap {
    constructor() {
        super();
    }

    enqueue = (priority, value) => this.insert(priority, value);
    dequeue = () => this.remove();
    isEmpty = () => this.heap.length <= 0;
}

let input = fs.readFileSync('example.txt').toString();

let readline = input.split(`\n`);

let lineSplit = readline[0].split(' ');

let M = parseInt(lineSplit[0]);
let N = parseInt(lineSplit[1]);
let H = parseInt(lineSplit[2]);

let tomatoBox = Array.from(Array(H), () => Array.from(Array(N), () => new Array(M).fill(0)));

let queue = new PriorityQeuue();
let visited = Array.from(Array(H), () => Array.from(Array(N), () => new Array(M).fill(false)));

let amount = 0;

for (let i = 0 ; i < H ; i++) {
    for (let j = 0 ; j < N ; j++) {
        lineSplit = readline[j+i*N+1].split(' ');
        for (let k = 0 ; k < M ; k++) {
            tomatoBox[i][j][k] = parseInt(lineSplit[k]);
            if (tomatoBox[i][j][k] === 1) {
                queue.enqueue(0, [i, j, k]);
                visited[i][j][k] = true;
            }

            if (tomatoBox[i][j][k] === 0) {
                amount += 1;
            }
        }
    }
}

let D = [[0,0,1],[0,0,-1],[-1,0,0],[1,0,0],[0,-1,0],[0,1,0]];

let ans = 0;
let count = 0;

while(!queue.isEmpty()) {
    const now = queue.dequeue();
    const x = now.value[0];
    const y = now.value[1];
    const z = now.value[2];
    const day = now.key;

    // console.log(queue);

    if (ans <= day) ans = day;

    for (let d = 0 ; d < 6 ; d++) {
        const nx = x + D[d][0];
        const ny = y + D[d][1];
        const nz = z + D[d][2];

        if (nx < 0 || ny < 0 || nz < 0 || nx >= H || ny >= N || nz >= M || visited[nx][ny][nz] || tomatoBox[nx][ny][nz] === -1) continue;

        queue.enqueue(day+1, [nx, ny, nz]);
        count += 1;
        visited[nx][ny][nz] = true;
    }
}

if (amount !== count) console.log(-1)
else console.log(ans);
