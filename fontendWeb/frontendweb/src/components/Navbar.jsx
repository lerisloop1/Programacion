import { AppBar, Toolbar, Typography, Button, Stack } from "@mui/material";
import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <AppBar position="fixed" sx={{
            backgroundColor: "#0d47a1", // Fondo azul (puedes cambiarlo)
            width: "100%",
            boxShadow: "0 2px 5px rgba(0,0,0,0.3)"}} >
            <Toolbar>
                <Typography variant="h6" sx={{ flexGrow: 1 }}>
                    Moto App
                </Typography>

                <Stack direction="row" spacing={2}>
                    <Button color="inherit" component={Link} to="/personas">
                        Personas
                    </Button>
                    <Button color="inherit" component={Link} to="/motos">
                        Motos
                    </Button>
                    <Button color="inherit" component={Link} to="/compras">
                        Compras
                    </Button>
                </Stack>
            </Toolbar>
        </AppBar>
    );
}