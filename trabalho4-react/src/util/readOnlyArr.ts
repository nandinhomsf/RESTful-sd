// const { data: categorias } = useCategorias();

const umArray: string[] = ["1", "2", "3"];
// const readOnlyArr: ReadonlyArray<String> = umArray;

const readOnlyArr = ["1", "2", "3"] as const; 
// const readOnlyArr: readonly ["1", "2", "3"]; 

// umArray[0] = "9";     => OK
//readOnlyArr[0] = "9";  => Erro

console.log(umArray, readOnlyArr)

export default readOnlyArr;