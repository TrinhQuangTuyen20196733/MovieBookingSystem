import classNames from "classnames/bind";
import styles from "./MemberSection.module.scss";
import Image from "~/components/Image";
import images from "~/assets/images";
import Button from "~/components/Button/Button";
const cx = classNames.bind(styles);
function MemberSection() {
  return (
    <div className={cx("wrapper")}>
      <Image
        className={cx("member-title")}
        src={images.BHDStarMember}
        alt="BHDStarMember"
        width="350px"
        height="auto"
      />
      <div className={cx("list-car-member")}>
        <Image
          className={cx("star-member")}
          src={images.STAR}
          alt="STAR member"
          width="360px"
          height="auto"
        />
        <Image
          className={cx("gold-member")}
          src={images.GOLD}
          alt="GOLD member"
          width="360px"
          height="auto"
        />

        <Image
          className={cx("diamond-member")}
          src={images.DIAMOND}
          alt="DIAMOND member"
          width="360px"
          height="auto"
        />
      </div>
      <Button text isMemberRegister>
        ĐĂNG KÍ NGAY
      </Button>
    </div>
  );
}

export default MemberSection;
