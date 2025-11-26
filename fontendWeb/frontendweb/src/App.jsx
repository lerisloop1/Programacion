import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Personas from "./pages/Personas";
import Motos from "./pages/Motos";
import Compras from "./pages/Compras";
import RegistrarCompra from "./pages/RegistrarCompra";
import "./App.css";
import RegistrarPersona from "./pages/RegistrarPersona.jsx";

export default function App() {

    return (
        <BrowserRouter>
            <Navbar />
            <div className="wave"> </div>
            <div className="wave2"> </div>
            <div className="container">
            <Routes >
                <Route path="/personas" element={<Personas />} />
                <Route path="/motos" element={<Motos />} />
                <Route path="/compras" element={<Compras />} />
                <Route path="/registrar-compra" element={<RegistrarCompra />} />
                <Route path="/registrar-persona" element={<RegistrarPersona />} />


            </Routes>
            </div>
        </BrowserRouter>
    );

}
