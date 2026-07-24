import React from "react";
import "./App.css";

import Books from "./Components/Books";
import Blogs from "./Components/Blogs";
import Courses from "./Components/Courses";

function App() {

  const showBooks = true;
  const showBlogs = true;
  const showCourses = true;

  return (
    <div className="container">

      {showCourses && <Courses />}

      {showBooks && <Books />}

      {showBlogs && <Blogs />}

    </div>
  );
}

export default App;