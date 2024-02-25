import NavBar from "./NavBar";

const Profile = ({ user, setUser }) => {

    return ( <div>
        <NavBar user={user} setUser={setUser}/>
        Profile Page
    </div> );
}
 
export default Profile;