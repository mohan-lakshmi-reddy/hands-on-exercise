import React from "react";

const blogs = [
  {
    id: 1,
    title: "React Learning",
    author: "Stephen Biz",
    content: "Welcome to learning React!"
  },
  {
    id: 2,
    title: "Installation",
    author: "Schwzdenier",
    content: "You can install React from npm."
  }
];

function Blogs() {
  return (
    <div className="box">
      <h1>Blog Details</h1>

      {blogs.map((blog) => (
        <div key={blog.id}>
          <h2>{blog.title}</h2>
          <h4>{blog.author}</h4>
          <p>{blog.content}</p>
        </div>
      ))}
    </div>
  );
}

export default Blogs;