import { useRef } from "react";

interface Props {
  nome: string;
  onRetrieveNome: (nome: string) => void;
}

const Pesquisa = ({ nome, onRetrieveNome }: Props) => {
  const pesqRef = useRef<HTMLInputElement>(null);
  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();
        onRetrieveNome(pesqRef.current?.value!);
      }}
      className="d-flex mb-3"
      role="search"
    >
      <input
        ref={pesqRef}
        className="form-control me-2"
        placeholder="Pesquisa..."
        aria-label="Search"
        defaultValue={nome}
      />
      <button className="btn btn-outline-success" type="submit">
        Search
      </button>
    </form>
  );
};

export default Pesquisa;
