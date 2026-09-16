{

type MyError = {code: number, description: string}

function add(x: number, y:number): number | MyError {
  if(x < 0 || y < 0) {
    return {code: 1, description: "The values must be positive"};
  }
  return x + y
}

console.log(add(2,3))


const ret: number | MyError = add(-3, 1)


function xpto(somethingLikeAliceOrBob: {name: string, nbr: number}) {

}


var alice: {name: string, nbr: number, length: number} = {
  name: 'Alice',
  nbr: 1234,
  length: 123
}

var bob: {name: string, nbr: number, age: number} = {
  name: 'Bob',
  nbr: 1234,
  age: 25
}

xpto(alice)
xpto(bob)

alice = bob;

let other:any = alice

console.log(other.age)



// console.log(add("2", "4"))
// console.log(add(true, true))
// console.log(add([1,2], [3,4]))
// console.log(add({a:1}, {b:2}))
// console.log(add())


const aStringArray: Array<string> = ['hello', 'world']
const aNumberArray: Array<number> = [0, 1, 2]
const anArrayOfArrayOfStrings: Array<Array<string>> = [['hello', 'world'], ['olá', 'mundo']]

const a = [1,"ABC", {}]



function isEmpty(input: {length:number}): boolean {
  return input.length === 0
}
isEmpty("a string") // valid because a string has a `length` property.
isEmpty("")
isEmpty([1]) // valid because an array has a `length` property.
isEmpty([])
isEmpty(alice)


type Predicate<T> = (input: T) => boolean

type PrimaryColor = "red" | "green" | "blue" | 2 | { name: string, age: number}
}