import React, { useEffect, useState } from 'react';
import '../styles/salary.css'; // Import your CSS file
import axios from 'axios';

const Salary = ({ user, setUser }) => {
  const [salaryDetails, setSalaryDetails] = useState({
    employeeName: "Vivek Kumar",
    paymentDate: "2023-11-26",
    totalSalary: 50000,
    description: "Monthly Salary",
    otherDeductions: 5000,
    netSalary: 30000,
    history: [
      { paymentDate: new Date(), amount: 5000 },
      { paymentDate: new Date(), amount: 5200 },
      // Add more history data as needed
    ],
  });

  useEffect(() => {
    const fetchSalaryDetails = async () => {
      try {
        const token = JSON.parse(localStorage.getItem('loggedInUser')).access_token; // Replace with your authorization token
        const facultyId = 1; // Replace with the actual faculty ID
        
        const response = await axios.get(`/api/v1/employee/salary/${facultyId}`, {
          headers: {
            "access-control-allow-origin" : "*",
            Authorization: `Bearer ${token}`,
          },
        });
        console.log("response: "+ JSON.stringify(response.data[2]));
        // Update state with the fetched data
        const history = response.data.slice(1).map(item => ({
          paymentDate: item.paymentDate,
          amount: item.amount,
          salarySlip: item.salarySlip
        }));

        setSalaryDetails({
          employeeName: user.first_name + " " + user.last_name,
          paymentDate: response.data[0].paymentDate,
          totalSalary: response.data[0].amount,
          description: response.data[0].description,
          otherDeductions: 5000,
          netSalary: response.data.amount,
          history: history,
        });
      } catch (error) {
        // Handle error
        console.error('Error fetching salary details:', error);
      }
    };

    fetchSalaryDetails();
  }, []);

  const handleDownload = async (month, salarySlip) => {
    console.log(`Downloading salary slip for ${month}`);

    window.open(salarySlip, '_blank');
  };
  console.log(salaryDetails);
  return (
    <div className="salary-page">
      <div className="salary-container">
        <div className="salary-header">
          <h1 className="salary-heading">Salary Details</h1>
          <p>Name: {salaryDetails.employeeName}</p>
          <p>Payment Date: {salaryDetails.paymentDate}</p>
        </div>
        <div className="total-salary">
          <h2 className="section-heading">Total Salary Breakdown</h2>
          <div className="salary-breakdown">
            <div>
              <p>Gross Salary:</p>
              <p>Taxes:</p>
              <p>Other Deductions:</p>
              <p className="net-salary">Net Salary:</p>
            </div>
            <div>
              <p>₹{salaryDetails.totalSalary}</p>
              <p>₹{salaryDetails.taxes}</p>
              <p>₹{salaryDetails.otherDeductions}</p>
              <p className="net-salary">₹{salaryDetails.netSalary}</p>
            </div>
          </div>
        </div>
        <div className="salary-history">
          <h2 className="section-heading">Salary Disbursement History</h2>
          <ul className="history-list">
            {salaryDetails.history.map((entry, index) => (
              <li key={index} className="history-item">
                <span>{new Date(entry.paymentDate).toLocaleDateString()}</span>
                <span>Amount: ₹{entry.amount}</span>
                <button onClick={() => handleDownload(new Date(entry.paymentDate).toLocaleDateString(), entry.salarySlip)}>
                  Download Slip
                </button>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Salary;
