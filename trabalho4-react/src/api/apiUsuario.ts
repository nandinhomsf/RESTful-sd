import Usuario from "../interfaces/Usuario";
import ApiGenerica from "./apiGenerica";

class ApiUsuario extends ApiGenerica<Usuario> {
  constructor() {
    super("/usuarios");
  }
}

export default ApiUsuario;