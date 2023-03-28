import IntroSection from "~/layouts/components/IntroSection";
import MovieSection from "~/layouts/components/MovieComponent";
import MemberSection from "~/layouts/components/MemberSection/MemberSection";
import EventSection from "~/layouts/components/EventSection";
function Home() {
  return (<>
  <IntroSection />
      <MovieSection />
      <MemberSection />
      <EventSection />
  </>
    
  )
}

export default Home;
