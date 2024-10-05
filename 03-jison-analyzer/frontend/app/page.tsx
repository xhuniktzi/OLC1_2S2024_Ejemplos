'use client'
import React, { useState } from 'react';
import axios from 'axios';

const Home = () => {
  const [inputText, setInputText] = useState('');
  const [outputText, setOutputText] = useState('');

  const handleParse = async () => {
    try {
      const response = await axios.post('http://localhost:3001/parse', { input: inputText });
      setOutputText(response.data.output); // Assumes the API response contains an 'output' field
    } catch (error) {
      console.error('Error parsing input:', error);
      setOutputText('Error parsing input.');
    }
  };

  const handleClear = () => {
    setInputText('');
    setOutputText('');
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <h1 className="text-3xl font-bold mb-6">Interpreter</h1>
      <div className="w-full max-w-4xl grid grid-cols-2 gap-4">
        {/* Input Textarea */}
        <textarea
          className="p-4 border border-gray-300 rounded-lg shadow-sm w-full h-64 resize-none focus:outline-none focus:ring-2 focus:ring-blue-500"
          placeholder="Write your input here..."
          value={inputText}
          onChange={(e) => setInputText(e.target.value)}
        />
        {/* Output Textarea (Non-editable) */}
        <textarea
          className="p-4 border border-gray-300 rounded-lg shadow-sm w-full h-64 resize-none bg-gray-200 focus:outline-none cursor-not-allowed"
          value={outputText}
          readOnly
        />
      </div>
      <div className="mt-4 flex space-x-4">
        <button
          onClick={handleParse}
          className="px-4 py-2 bg-blue-500 text-white font-semibold rounded-lg shadow hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
        >
          Parse Input
        </button>
        <button
          onClick={handleClear}
          className="px-4 py-2 bg-gray-500 text-white font-semibold rounded-lg shadow hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-gray-500 focus:ring-offset-2"
        >
          Clear
        </button>
      </div>
    </div>
  );
};

export default Home;
