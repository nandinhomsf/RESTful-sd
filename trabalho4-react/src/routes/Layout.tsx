import NavBar from "../components/NavBar";
import { Outlet } from "react-router-dom";

const Layout = () => {
  return (
    <>
      <NavBar />
      <div className="container mt-3">
        <Outlet />
      </div>
    </>
  );
};
export default Layout;
