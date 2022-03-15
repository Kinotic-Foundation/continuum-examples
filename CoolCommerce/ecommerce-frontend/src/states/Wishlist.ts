import Product from '@/domain/Product'

/**
 * Holds all data for the Wishlist
 */
export class Wishlist{

    private itemMap: Map<number, Product> = new Map()

    private _itemCount = 0

    public get items(): Product[] {
        return Array.from(this.itemMap.values())
    }

    public toggleItem(product: Product): void{
        if(!this.itemMap.has(product.id)){
            this.itemMap.set(product.id, product)
            this._itemCount++
        }else{
            this.itemMap.delete(product.id)
            this._itemCount--
        }
    }

    public containsProduct(product: Product): boolean{
        return this.itemMap.has(product.id)
    }

    get itemCount(): number {
        return this._itemCount
    }

}