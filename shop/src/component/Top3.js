// 가장 빠른 뉴스 3개.

export default function Top3({ data }) {
    return(
        <>
            <div className="col-md-4" >
               <p>{data.boardNumber}</p>
               <h4>{data.title}</h4>
               <p>{data.content}</p>
               <p>{data.favoriteCount}</p>
               <p>{data.writeEmail}</p>
            </div>
        </>
    )
}