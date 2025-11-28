import React from "react";
import UserList from "./components/UserList";

function App() {
  return (
    <div className="container" style={{padding:20}}>
      <h2>User CRUD (React + Axios)</h2>
      <UserList />
    </div>
  );
}

export default App;
